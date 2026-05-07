package domain.system_study_api.service.access_exam;

import domain.system_study_api.dto.password_active.AccessExamClientRequestDTO;
import domain.system_study_api.dto.password_active.PasswordExamRequestDTO;

import java.util.UUID;

public interface AccessExamService {
    void validateAccess(AccessExamClientRequestDTO request);

    void createPasswordExam(PasswordExamRequestDTO dto);

    void updateUserExam(UUID userExamId, AccessExamClientRequestDTO request);
}
