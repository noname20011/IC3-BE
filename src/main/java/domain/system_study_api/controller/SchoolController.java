package domain.system_study_api.controller;

import domain.system_study_api.dto.ResponseData;
import domain.system_study_api.dto.school.SchoolRequestDTO;
import domain.system_study_api.dto.school.SchoolResponseDTO;
import domain.system_study_api.helper.validators.uuid_validation.ValidUUID;
import domain.system_study_api.service.school.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/school/")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping("")
    public ResponseData<SchoolResponseDTO> add(@RequestBody @Valid SchoolRequestDTO body) {
        SchoolResponseDTO data = schoolService.addSchool(body);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Create successfully!", data);
    }

    @PatchMapping("/{schoolId}")
    public ResponseData<SchoolResponseDTO> putSchool(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID schoolId,
            @RequestBody @Valid SchoolRequestDTO body) {
        SchoolResponseDTO data = schoolService.putSchool(schoolId, body);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Update successfully!", data);
    }

    @DeleteMapping("/{schoolId}")
    public ResponseData<?> deleteSchool(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID schoolId) {
        schoolService.deleteSchool(schoolId);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Delete successfully!");
    }

    @GetMapping("")
    public ResponseData<List<SchoolResponseDTO>> getAllSchools() {
        List<SchoolResponseDTO> responseDTOS = schoolService.getAllSchools();
        return new ResponseData<>(HttpStatus.OK.value(), "Get all schools success!", responseDTOS);
    }

    @GetMapping("/{schoolId}")
    public ResponseData<SchoolResponseDTO> getSchool(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID schoolId) {
        SchoolResponseDTO responseDTO = schoolService.getSchoolById(schoolId);
        return new ResponseData<>(HttpStatus.OK.value(), "Get schools success!", responseDTO);
    }
}
