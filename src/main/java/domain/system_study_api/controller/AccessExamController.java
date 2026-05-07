package domain.system_study_api.controller;

import domain.system_study_api.dto.ResponseData;
import domain.system_study_api.dto.password_active.AccessExamClientRequestDTO;
import domain.system_study_api.dto.password_active.PasswordExamRequestDTO;
import domain.system_study_api.helper.validators.uuid_validation.ValidUUID;
import domain.system_study_api.service.access_exam.AccessExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/access-exam")
@RequiredArgsConstructor
public class AccessExamController {
    private final AccessExamService accessExamService;

    @PostMapping("/verify")
    public ResponseData<?> verifyAccess(@RequestBody AccessExamClientRequestDTO request) {
        accessExamService.validateAccess(request);
        return new ResponseData<>(HttpStatus.OK.value(), "Access has been verified");
    }

    @PostMapping("/setup")
    public ResponseData<?> createPassword(@ModelAttribute @Valid PasswordExamRequestDTO dto) {
        accessExamService.createPasswordExam(dto);
        return new ResponseData<>(HttpStatus.OK.value(), "Create Password Access successfully!");
    }

    @PatchMapping("/reset/{userExamId}")
    public ResponseData<?> putUserExam(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID userExamId
            , @RequestBody AccessExamClientRequestDTO dto) {
        accessExamService.updateUserExam(userExamId, dto);
        return new ResponseData<>(HttpStatus.OK.value(), "Create Password Access successfully!");
    }
}
