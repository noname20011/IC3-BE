package domain.system_study_api.controller;

import domain.system_study_api.dto.ResponseData;
import domain.system_study_api.dto.level.LevelRequestDTO;
import domain.system_study_api.dto.level.LevelResponseDTO;
import domain.system_study_api.helper.validators.uuid_validation.ValidUUID;
import domain.system_study_api.service.level.LevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/level/")
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;

    @PostMapping("")
    public ResponseData<LevelResponseDTO> add(@RequestBody @Valid LevelRequestDTO body) {
        LevelResponseDTO data = levelService.addLevel(body);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Create successfully!", data);
    }

    @PatchMapping("/{levelId}")
    public ResponseData<LevelResponseDTO> putLevel(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID levelId,
            @RequestBody @Valid LevelRequestDTO body) {
        LevelResponseDTO data = levelService.putLevel(levelId, body);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Update successfully!", data);
    }

    @DeleteMapping("/{levelId}")
    public ResponseData<?> deleteSchool(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID levelId) {
        levelService.deleteLevel(levelId);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Delete successfully!");
    }

    @GetMapping("")
    public ResponseData<List<LevelResponseDTO>> getAllLevels() {
        List<LevelResponseDTO> responseDTOS = levelService.getAllLevels();
        return new ResponseData<>(HttpStatus.OK.value(), "Get all levels success!", responseDTOS);
    }

    @GetMapping("/{levelId}")
    public ResponseData<LevelResponseDTO> getLevel(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID levelId) {
        LevelResponseDTO responseDTO = levelService.getLevelById(levelId);
        return new ResponseData<>(HttpStatus.OK.value(), "Get schools success!", responseDTO);
    }
}
