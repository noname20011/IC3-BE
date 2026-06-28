package domain.system_study_api.service.leaderboard;

import domain.system_study_api.dto.quiz_result.QuizResultResponseDTO;
import domain.system_study_api.dto.quiz_result.TopStudentDTO;
import domain.system_study_api.entity.QuizResult;
import domain.system_study_api.mapper.QuizResultMapper;
import domain.system_study_api.repository.LeaderBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<TopStudentDTO> getLeaderboardsByClassAndPart(UUID classId, UUID partId) {
        return repository.findByClassIdAndPartId(partId, classId);
    }

    @Override
    public Page<TopStudentDTO> getLeaderboardsByPart(UUID partId) {
        Pageable top20 = PageRequest.of(0, 20);
        return repository.findTop20ByPartId(partId, top20);
    }

    @Override
    public List<TopStudentDTO> getTop1EachPartBySchoolId(UUID schoolId) {
        return repository.getTop1ForEachPartAndSchool(schoolId);
    }
}
