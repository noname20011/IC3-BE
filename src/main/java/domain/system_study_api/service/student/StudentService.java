package domain.system_study_api.service.student;

import domain.system_study_api.dto.student.StudentResponseDTO;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface StudentService {
    void syncStudentsFromSheet(UUID classroomId) throws IOException;

    List<StudentResponseDTO> getStudents(UUID classroomId);

    StudentResponseDTO getStudent(UUID studentId);
}
