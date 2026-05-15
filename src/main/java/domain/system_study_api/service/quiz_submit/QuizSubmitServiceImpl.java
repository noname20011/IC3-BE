package domain.system_study_api.service.quiz_submit;

import domain.system_study_api.dto.quiz_result.QuizResultRequestDTO;
import domain.system_study_api.dto.quiz_result.QuizResultResponseDTO;
import domain.system_study_api.dto.quiz_result.TopStudentDTO;
import domain.system_study_api.entity.Part;
import domain.system_study_api.entity.QuizResult;
import domain.system_study_api.entity.Student;
import domain.system_study_api.listener.QuizSubmittedEvent;
import domain.system_study_api.mapper.QuizResultMapper;
import domain.system_study_api.repository.LeaderBoardRepository;
import domain.system_study_api.repository.PartRepository;
import domain.system_study_api.repository.QuizSubmitRepository;
import domain.system_study_api.repository.StudentRepository;
import domain.system_study_api.service.GoogleSheetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizSubmitServiceImpl implements QuizSubmitService {

    private final QuizSubmitRepository quizRepository;
    private final LeaderBoardRepository lbRepository;
    private final StudentRepository studentRepository;
    private final QuizResultMapper leaderboardMapper;
    private final SimpMessagingTemplate messagingTemplate;
    private final PartRepository partRepository;
    private final QuizResultMapper quizResultMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public QuizResultResponseDTO submitQuiz(QuizResultRequestDTO request) {
        // 1. Lưu kết quả vào MySQL
        Student student = studentRepository.findByIdOrThrow(request.getStudentId());

        Part part = partRepository.findByIdOrThrow(request.getPartId());
        QuizResult result = QuizResult.builder()
                .student(student)
                .score(request.getScore())
                .timeSpent(request.getTimeSpent())
                .part(part) // OT 1, 2 hoặc 3
                .build();

        result = quizRepository.save(result);

        // 2. Gửi tín hiệu WebSocket để update Leaderboard Realtime
        List<TopStudentDTO> leaderboardData = lbRepository.findByClassIdAndPartId(request.getPartId(), request.getClassId());

        // Chúng ta gửi về topic của lớp đó để các bạn cùng lớp thấy ngay
        // 3. "Bắn" data qua WebSocket tới kênh /topic/leaderboard
        String destination = String.format("/topic/leaderboard/%s/%s", request.getClassId(), request.getPartId());
        messagingTemplate.convertAndSend(destination, leaderboardData);


        // 4. Ghi lên Google Sheet (Chạy bất đồng bộ - Async)
        // Bắn Event thay vì gọi trực tiếp Async
        // Spring sẽ giữ Event này lại cho đến khi Transaction thành công
        eventPublisher.publishEvent(new QuizSubmittedEvent(result.getId()));

        return quizResultMapper.mapToResponseDto(result);
    }
}
