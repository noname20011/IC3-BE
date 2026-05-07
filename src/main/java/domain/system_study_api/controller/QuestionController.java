package domain.system_study_api.controller;

import domain.system_study_api.dto.ResponseData;
import domain.system_study_api.dto.question.QuestionRequestDTO;
import domain.system_study_api.dto.question.QuestionResponseDTO;
import domain.system_study_api.helper.validators.uuid_validation.ValidUUID;
import domain.system_study_api.service.question.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/question/")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("")
    public ResponseData<QuestionResponseDTO> addQuestion(@ModelAttribute @Valid QuestionRequestDTO body) {
        QuestionResponseDTO data = questionService.addQuestion(body);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Create successfully!", data);
    }

    @PatchMapping("/{questionId}")
    public ResponseData<QuestionResponseDTO> putQuestion(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID questionId,
            @ModelAttribute @Valid QuestionRequestDTO body) {
        QuestionResponseDTO data = questionService.putQuestion(questionId, body);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Update successfully!", data);
    }

    @DeleteMapping("/{questionId}")
    public ResponseData<?> delete(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID questionId) {
        questionService.deleteQuestion(questionId);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Delete successfully!");
    }

    @GetMapping("/by-part/{partId}")
    public ResponseData<List<QuestionResponseDTO>> getAllQuestions(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID partId) {
        List<QuestionResponseDTO> responseDTOS = questionService.getAllQuestions(partId);
        return new ResponseData<>(HttpStatus.OK.value(), "Get all questions success!", responseDTOS);
    }

    @GetMapping("/{questionId}")
    public ResponseData<QuestionResponseDTO> getQuestion(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID questionId) {
        QuestionResponseDTO responseDTO = questionService.getQuestion(questionId);
        return new ResponseData<>(HttpStatus.OK.value(), "Get schools success!", responseDTO);
    }
}
