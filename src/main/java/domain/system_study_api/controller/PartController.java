package domain.system_study_api.controller;

import domain.system_study_api.dto.ResponseData;
import domain.system_study_api.dto.part.PartRequestDTO;
import domain.system_study_api.dto.part.PartResponseDTO;
import domain.system_study_api.helper.validators.uuid_validation.ValidUUID;
import domain.system_study_api.service.part.PartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/part/")
@RequiredArgsConstructor
public class PartController {
    private final PartService partService;

    @PostMapping("")
    public ResponseData<PartResponseDTO> add(@RequestBody @Valid PartRequestDTO body) {
        PartResponseDTO data = partService.addPart(body);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Create successfully!", data);
    }

    @PatchMapping("/{partId}")
    public ResponseData<PartResponseDTO> putPart(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID partId,
            @RequestBody @Valid PartRequestDTO body) {
        PartResponseDTO data = partService.putPart(partId, body);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Update successfully!", data);
    }

    @DeleteMapping("/{partId}")
    public ResponseData<?> deleteSchool(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID partId) {
        partService.deletePart(partId);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Delete successfully!");
    }

    @GetMapping("/by-level/{levelId}")
    public ResponseData<List<PartResponseDTO>> getAllParts(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID levelId) {
        List<PartResponseDTO> responseDTOS = partService.getAllParts(levelId);
        return new ResponseData<>(HttpStatus.OK.value(), "Get all parts success!", responseDTOS);
    }

    @GetMapping("/{partId}")
    public ResponseData<PartResponseDTO> getPart(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID partId) {
        PartResponseDTO responseDTO = partService.getPartById(partId);
        return new ResponseData<>(HttpStatus.OK.value(), "Get schools success!", responseDTO);
    }
}
