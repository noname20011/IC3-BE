package domain.system_study_api.controller;

import domain.system_study_api.dto.ResponseData;
import domain.system_study_api.dto.classroom.ClassroomResponseDTO;
import domain.system_study_api.helper.validators.uuid_validation.ValidUUID;
import domain.system_study_api.service.classroom.ClassroomService;
import domain.system_study_api.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sync")
@RequiredArgsConstructor
public class SheetSyncController {
    private final ClassroomService classroomService;
    private final StudentService studentService;


    @PostMapping("/school/{schoolId}")
    public ResponseData<?> sync (
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID schoolId ) throws IOException {
        classroomService.scanAndUpdateClassrooms(schoolId);
        List<ClassroomResponseDTO> classrooms = classroomService.getClassrooms(schoolId);
        // 1. Đồng bộ Tab (Classroom) trước
        classroomService.scanAndUpdateClassrooms(schoolId);

        // 2. Lấy danh sách lớp vừa đồng bộ để quét học sinh từng lớp
        List<ClassroomResponseDTO> classroomData = classroomService.getClassrooms(schoolId);
        for (ClassroomResponseDTO classroom : classroomData) {
            studentService.syncStudentsFromSheet(classroom.getId());
        }

        return new ResponseData<>(HttpStatus.CREATED.value(), "Sync classroom and student successfully!");
    }
}
