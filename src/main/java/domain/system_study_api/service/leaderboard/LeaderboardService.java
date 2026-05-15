package domain.system_study_api.service.leaderboard;

import domain.system_study_api.dto.quiz_result.QuizResultResponseDTO;
import domain.system_study_api.dto.quiz_result.TopStudentDTO;

import java.util.List;
import java.util.UUID;

public interface LeaderboardService {
    List<TopStudentDTO> getTopStudentEachLevel();
    List<QuizResultResponseDTO> getStudentIdOrderByCreatedAtDesc(UUID studentId);

    List<TopStudentDTO> getLeaderboardsByClassAndPart(UUID classId, UUID partId);
    List<TopStudentDTO> getLeaderboardsByPart(UUID partId);

}
