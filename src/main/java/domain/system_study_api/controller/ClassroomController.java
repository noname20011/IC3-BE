package domain.system_study_api.controller;

import domain.system_study_api.dto.ResponseData;
import domain.system_study_api.dto.classroom.ClassroomResponseDTO;
import domain.system_study_api.helper.validators.uuid_validation.ValidUUID;
import domain.system_study_api.service.classroom.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classrooms")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;

    @GetMapping("/school/{schoolId}")
    public ResponseData<List<ClassroomResponseDTO>> getAllClassrooms(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID schoolId ) {
        List<ClassroomResponseDTO> classrooms = classroomService.getClassrooms(schoolId);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Get all classroom successfully!", classrooms);
    }
}
