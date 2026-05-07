package domain.system_study_api.controller;

import domain.system_study_api.dto.ResponseData;
import domain.system_study_api.dto.quiz_result.QuizResultRequestDTO;
import domain.system_study_api.dto.quiz_result.QuizResultResponseDTO;
import domain.system_study_api.service.quiz_submit.QuizSubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quiz-result/")
@RequiredArgsConstructor
public class QuizSubmitController {
    private final QuizSubmitService quizResultService;

    @PostMapping("")
    public ResponseData<QuizResultResponseDTO> save(@RequestBody QuizResultRequestDTO quizResult) {
        QuizResultResponseDTO responseDTO = quizResultService.submitQuiz(quizResult);
        return new ResponseData<QuizResultResponseDTO>(HttpStatus.OK.value(), "Submitted Successfully", responseDTO);
    }
}
