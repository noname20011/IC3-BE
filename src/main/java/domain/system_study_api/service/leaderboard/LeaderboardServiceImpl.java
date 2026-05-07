package domain.system_study_api.service.leaderboard;

import domain.system_study_api.dto.quiz_result.QuizResultResponseDTO;
import domain.system_study_api.dto.quiz_result.TopStudentDTO;
import domain.system_study_api.entity.QuizResult;
import domain.system_study_api.mapper.QuizResultMapper;
import domain.system_study_api.repository.LeaderBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderBoardRepository repository;
    private final QuizResultMapper leaderboardMapper;


    @Override
    public List<TopStudentDTO> getTopStudentEachLevel() {
        List<TopStudentDTO> data = repository.findTopStudentForEachLevel();
        return data;
    }

    @Override
    public List<QuizResultResponseDTO> getStudentIdOrderByCreatedAtDesc(UUID studentId) {
        List<QuizResult> data = repository.findByStudentIdOrderByCreatedAtDesc(studentId);
        return leaderboardMapper.mapToListResponseDtos(data);
    }

    @Override
    public List<QuizResultResponseDTO> getLeaderboardsByClassAndPart(UUID classId, UUID partId) {
        List<QuizResult> data = repository.findLeaderboardByClassAndPart(classId, partId);
        return leaderboardMapper.mapToListResponseDtos(data);
    }

    @Override
    public List<QuizResultResponseDTO> getLeaderboardsByPart(UUID partId) {
        List<QuizResult> data = repository.findLeaderboardByPart(partId);
        return leaderboardMapper.mapToListResponseDtos(data);
    }
}
