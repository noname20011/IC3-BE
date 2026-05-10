package domain.system_study_api.service.access_exam;

import domain.system_study_api.constants.AppConstant;
import domain.system_study_api.constants.PasswordStatus;
import domain.system_study_api.dto.password_active.AccessExamClientRequestDTO;
import domain.system_study_api.dto.password_active.PasswordExamRequestDTO;
import domain.system_study_api.entity.DeviceRegistration;
import domain.system_study_api.entity.PasswordExam;
import domain.system_study_api.entity.Student;
import domain.system_study_api.entity.UserAccessExam;
import domain.system_study_api.exception.BusinessException;
import domain.system_study_api.mapper.PasswordExamMapper;
import domain.system_study_api.repository.DeviceRegistrationRepository;
import domain.system_study_api.repository.PasswordExamRepository;
import domain.system_study_api.repository.StudentRepository;
import domain.system_study_api.repository.UserAccessExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccessExamServiceImpl implements AccessExamService {

    private final PasswordExamRepository passwordRepository;
    private final UserAccessExamRepository userAccessRepository;
    private final DeviceRegistrationRepository deviceRepository;
    private final PasswordExamMapper passwordExamMapper;
    private final StudentRepository sRepository;

    @Transactional
    @Override
    public void validateAccess(AccessExamClientRequestDTO request) {
        // 1. Kiểm tra Password tồn tại
        PasswordExam passExam = passwordRepository.findByPassword(request.getPasswordExam())
                .orElseThrow(() -> new BusinessException("Password incorrect!"));

        // 2. Check Expiry (Ngày hết hạn)
        if (passExam.getExpireDate() != null && passExam.getExpireDate().isBefore(LocalDate.now())) {
            throw new BusinessException("Password has expired!");
        }

        // 3. Check Allow Skip Identify, else check access Part (Metadata)
        if(!passExam.getAllowedSkipIdentify()) {
            validatePartAccess(passExam, String.valueOf(request.getPartId()));

            // 4. Check  Secret Answer Question
            Optional<UserAccessExam> accessExam = userAccessRepository.findUserAccessExamByStudentId(request.getStudentId());
            if (accessExam.isPresent()) {
                if (!request.getSecretAnswer().equals(accessExam.get().getSecretAnswer())) {
                    throw new BusinessException("Câu hỏi bí mật không chính xác");
                }

                // 5. Check Browser Fingerprint
                checkFingerPrintExpired(request.getFingerprintId(), accessExam.get());
            } else {
                // Insert new UserAccessExam
                createUserAccessExam(request, passExam);
            }
        }

    }


    @Transactional
    @Override
    public void createPasswordExam(PasswordExamRequestDTO dto) {
        PasswordExam pass = passwordExamMapper.mapToEntity(dto);
//        String password = passwordEncoder.encode(pass.getPassword());

//        pass.setPassword(password);
        pass.setStatus(PasswordStatus.ACTIVATING);
        passwordRepository.save(pass);
    }

    @Transactional
    @Override
    public void updateUserExam(UUID userExamIdd, AccessExamClientRequestDTO request) {
        UserAccessExam accessExam = userAccessRepository.findByIdOrThrow(userExamIdd);
        if (!request.getSecretAnswer().isEmpty()) {
            // Hash secret answer trước khi lưu
//            accessExam.setSecretAnswer(passwordEncoder.encode(request.getSecretAnswer()));
            userAccessRepository.save(accessExam);
        }
    }

    /**
     * Kiểm tra quyền truy cập tổng thể
     */

    private void validatePartAccess(PasswordExam passActive, String targetPartId) {
        if (Boolean.TRUE.equals(passActive.getAllowedAll())) return;

        Map<String, Object> metadata = passActive.getMetadata();
        if (metadata == null || !metadata.containsKey("partIds")) {
            throw new BusinessException("Password hasn't accessed this part!");
        }

        List<String> allowedPartIds = (List<String>) metadata.get("partIds");
        if (!allowedPartIds.contains(targetPartId)) {
            throw new BusinessException("Bạn không có quyền truy cập vào nội dung này");
        }
    }

    @Transactional
    protected void createUserAccessExam(AccessExamClientRequestDTO request, PasswordExam passExam) {
        Student student = sRepository.findByIdOrThrow(request.getStudentId());
        UserAccessExam access = new UserAccessExam();

        access.setPasswordExam(passExam);
        access.setStudent(student);
        // Hash secret answer trước khi lưu
//        access.setSecretAnswer(passwordEncoder.encode(request.getSecretAnswer()));
        access = userAccessRepository.save(access);

        // Save User's Device Registration
        DeviceRegistration deviceRegistration = new DeviceRegistration();
        deviceRegistration.setFingerprintId(request.getFingerprintId());
        deviceRegistration.setUserAccessExam(access);
        deviceRegistration.setNextSessionAt(LocalDateTime.now());
        deviceRepository.save(deviceRegistration);
    }

    private void checkFingerPrintExpired (String fingerprintId, UserAccessExam accessExam) {

        // Lấy device trực tiếp từ hồ sơ sinh viên
        DeviceRegistration data = accessExam.getDevice();
        if (data == null) return;

        if (!data.getFingerprintId().equals(fingerprintId)) {
            if (LocalDateTime.now().isBefore(data.getNextSessionAt())) {
                throw new BusinessException("You cannot change device until: " + data.getNextSessionAt());
            } else {
                // Đã hết thời gian chờ -> Cập nhật thiết bị mới
                data.setFingerprintId(fingerprintId);
                data.setNextSessionAt(LocalDateTime.now().plusHours(AppConstant.NEXT_SESSION_AT));
                deviceRepository.save(data);
            }
        }
    }
}
