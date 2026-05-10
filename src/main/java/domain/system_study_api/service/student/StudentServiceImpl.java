package domain.system_study_api.service.student;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import domain.system_study_api.dto.student.StudentResponseDTO;
import domain.system_study_api.entity.Classroom;
import domain.system_study_api.entity.Student;
import domain.system_study_api.mapper.StudentMapper;
import domain.system_study_api.repository.ClassroomRepository;
import domain.system_study_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final Sheets sheetsService;
    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;
    private final StudentMapper studentMapper;

    @Transactional
    @Override
    public void syncStudentsFromSheet(UUID classroomId) throws IOException {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lớp học"));

        String spreadsheetId = classroom.getSchool().getSpreadsheetId();
        // Vùng dữ liệu: TênTab!A2:C (STT, Họ, Tên)
        String range = classroom.getName() + "!A2:C";

        // 1. Đọc dữ liệu từ Google Sheet
        ValueRange response = sheetsService.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> rows = response.getValues();

        if (rows == null || rows.isEmpty()) {
            log.warn("Tab {} không có dữ liệu học sinh", classroom.getName());
            return;
        }

        // 2. Lấy dữ liệu hiện tại trong DB để đối soát
        List<Student> currentDbStudents = studentRepository.findByClassroomIdOrderByExternalIdAsc(classroomId);
        Map<Integer, Student> dbStudentMap = currentDbStudents.stream()
                .collect(Collectors.toMap(Student::getExternalId, s -> s));

        Set<Integer> sttOnSheet = new HashSet<>();

        // 3. Quét từng dòng từ Sheet
        for (List<Object> row : rows) {
            if (row.size() < 3 || row.get(0).toString().isEmpty()) continue;

            try {
                Integer stt = Integer.parseInt(row.get(0).toString().trim());
                String lastName = row.get(1).toString().trim();
                String firstName = row.get(2).toString().trim();
                sttOnSheet.add(stt);

                if (dbStudentMap.containsKey(stt)) {
                    // TRƯỜNG HỢP: ĐÃ CÓ TRONG DB -> Cập nhật nếu đổi tên
                    Student student = dbStudentMap.get(stt);
                    if (!student.getFirstName().equalsIgnoreCase(firstName) ||
                            !student.getLastName().equalsIgnoreCase(lastName)) {
                        student.setFirstName(firstName);
                        student.setLastName(lastName);
                        studentRepository.save(student);
                        log.info("Updated Student STT {}: {} {}", stt, lastName, firstName);
                    }
                } else {
                    // TRƯỜNG HỢP: CHƯA CÓ -> Thêm mới
                    Student newStudent = Student.builder()
                            .externalId(stt)
                            .lastName(lastName)
                            .firstName(firstName)
                            .classroom(classroom)
                            .build();
                    studentRepository.save(newStudent);
                    log.info("Inserted New Student STT {}: {} {}", stt, lastName, firstName);
                }
            } catch (NumberFormatException e) {
                log.error("Dòng dữ liệu lỗi STT: {}", row.get(0));
            }
        }

        // 4. TRƯỜNG HỢP XÓA: Có trong DB nhưng không còn STT đó trên Sheet
        List<Student> toDelete = currentDbStudents.stream()
                .filter(s -> !sttOnSheet.contains(s.getExternalId()))
                .collect(Collectors.toList());

        if (!toDelete.isEmpty()) {
            studentRepository.deleteAll(toDelete);
            log.info("Deleted {} students not found on Sheet", toDelete.size());
        }
    }

    @Override
    public List<StudentResponseDTO> getStudents(UUID classroomId) {
        List<Student> data = studentRepository.findByClassroomIdOrderByExternalIdAsc(classroomId);

        return studentMapper.mapToListResponseDtos(data);
    }

    @Override
    public StudentResponseDTO getStudent(UUID studentId) {
        Student data = studentRepository.findByIdOrThrow(studentId);
        return studentMapper.mapToResponseDto(data);
    }
}
