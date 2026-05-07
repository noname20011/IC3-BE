package domain.system_study_api.service.quiz_submit;

import domain.system_study_api.dto.quiz_result.QuizResultRequestDTO;
import domain.system_study_api.dto.quiz_result.QuizResultResponseDTO;

public interface QuizSubmitService {
    QuizResultResponseDTO submitQuiz(QuizResultRequestDTO request);

}
