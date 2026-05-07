package domain.system_study_api.controller;

import domain.system_study_api.dto.ResponseData;
import domain.system_study_api.dto.student.StudentResponseDTO;
import domain.system_study_api.helper.validators.uuid_validation.ValidUUID;
import domain.system_study_api.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/class/{classroomId}")
    public ResponseData<List<StudentResponseDTO>> getAllStudents(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID classroomId ) {
        List<StudentResponseDTO> data = studentService.getStudents(classroomId);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Get all student successfully!", data);

    }

    @GetMapping("/{studentId}")
    public ResponseData<StudentResponseDTO> getStudent(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID studentId ) {
        StudentResponseDTO data = studentService.getStudent(studentId);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Get student successfully!", data);

    }
}
