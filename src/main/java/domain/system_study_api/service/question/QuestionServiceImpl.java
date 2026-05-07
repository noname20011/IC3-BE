package domain.system_study_api.service.question;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.system_study_api.dto.question.QuestionRequestDTO;
import domain.system_study_api.dto.question.QuestionResponseDTO;
import domain.system_study_api.entity.Question;
import domain.system_study_api.mapper.QuestionMapper;
import domain.system_study_api.repository.QuestionRepository;
import domain.system_study_api.service.AsyncPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AsyncPhotoService asyncPhotoService;
    private final QuestionMapper questionMapper;
    private final ObjectMapper objectMapper;

    @Override
    public List<QuestionResponseDTO> getAllQuestions(UUID partId) {
        List<Question> questions = questionRepository.findAllByPartId(partId);
        log.info("Get all questions successfully!  {} ", questions);
        return questionMapper.mapToListResponseDtos(questions);
    }

    @Override
    public QuestionResponseDTO getQuestion(UUID questionId) {
        Question question = questionRepository.findByIdOrThrow(questionId);
        log.info("Get question successfully!  {} ", question);
        return questionMapper.mapToResponseDto(question);
    }

    @Override
    public QuestionResponseDTO addQuestion(QuestionRequestDTO requestDTO) {
//        try {
//            // Chuyển chuỗi JSON thành Map
//            Map<String, Object> metadataMap = objectMapper.readValue(
//                    requestDTO.getMetadata(),
//                    new TypeReference<Map<String, Object>>() {}
//            );
//
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Format metadata don't match!");
//        }
        // Tiếp tục gán vào Entity
        Question question = questionMapper.mapToEntity(requestDTO);

        //        Check if add Photo to execute add Photo
        if (requestDTO.getImageFile() != null) {
            CompletableFuture<String> future = asyncPhotoService.uploadPhoto(requestDTO.getImageFile());
            String imageUrl = future.join();
            question.setImageUrl(imageUrl);
        }

        Question saved = questionRepository.save(question);
        log.info("Add question successfully!  {} ", saved);
        return questionMapper.mapToResponseDto(saved);
    }

    @Override
    public QuestionResponseDTO putQuestion(UUID questionId, QuestionRequestDTO requestDTO) {
//        try {
//            // Chuyển chuỗi JSON thành Map
//            Map<String, Object> metadataMap = objectMapper.readValue(
//                    requestDTO.getMetadata(),
//                    new TypeReference<Map<String, Object>>() {}
//            );
//
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Format metadata don't match!");
//        }
        Question question = questionMapper.mapToEntity(requestDTO);
//        question.setMetadata(metadataMap);

        //        Check if add Photo to execute add Photo
        if (requestDTO.getImageFile() != null) {
            Question getEntity = questionRepository.findByIdOrThrow(questionId);
            log.info(getEntity.getImageUrl());
            if (getEntity.getImageUrl() != null) {
                asyncPhotoService.deletePhoto(getEntity.getImageUrl());
                question.setImageUrl(null);
            }

            CompletableFuture<String> future = asyncPhotoService.uploadPhoto(requestDTO.getImageFile());
            String imageUrl = future.join();
            question.setImageUrl(imageUrl);
        }
        Question saved = questionRepository.save(question);
        log.info("Update question successfully!  {} ", saved);
        return questionMapper.mapToResponseDto(saved);
    }

    public void deleteQuestion(UUID id) {
        questionRepository.deleteById(id);
        log.info("Delete question successfully!  {} ", id);
    }
}
