package domain.system_study_api.service.question;

import domain.system_study_api.dto.question.QuestionRequestDTO;
import domain.system_study_api.dto.question.QuestionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface QuestionService {

    List<QuestionResponseDTO> getAllQuestions(UUID partId);
    QuestionResponseDTO getQuestion(UUID questionId);
    QuestionResponseDTO addQuestion(QuestionRequestDTO requestDTO);
    QuestionResponseDTO putQuestion(UUID questionId, QuestionRequestDTO requestDTO);
    void deleteQuestion(UUID questionId);
}
