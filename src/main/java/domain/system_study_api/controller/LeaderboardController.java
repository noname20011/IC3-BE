package domain.system_study_api.controller;

import domain.system_study_api.dto.ResponseData;
import domain.system_study_api.dto.quiz_result.QuizResultResponseDTO;
import domain.system_study_api.dto.quiz_result.TopStudentDTO;
import domain.system_study_api.helper.validators.uuid_validation.ValidUUID;
import domain.system_study_api.service.leaderboard.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/leaderboard/")
@RequiredArgsConstructor
public class LeaderboardController {
    private final LeaderboardService leaderboardService;

    @GetMapping("/top-each-level")
    public ResponseData<List<TopStudentDTO>> getTopEachLevel() {
        List<TopStudentDTO> responseDTOS = leaderboardService.getTopStudentEachLevel();
        return new ResponseData<>(HttpStatus.OK.value(), "Get top each level successfully!", responseDTOS);
    }

    @GetMapping("/by-part/{partId}")
    public ResponseData<List<TopStudentDTO>> getLeaderboardByPart(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID partId) {
        List<TopStudentDTO> responseDTO = leaderboardService.getLeaderboardsByPart(partId);
        return new ResponseData<>(HttpStatus.OK.value(), "Get leaderboard by part successfully!", responseDTO);
    }

    @GetMapping("/by-class/{classId}/part/{partId}")
    public ResponseData<List<TopStudentDTO>> getLeaderboardByClassAndPart(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID classId,
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID partId) {
        List<TopStudentDTO> responseDTO = leaderboardService.getLeaderboardsByClassAndPart(classId, partId);
        return new ResponseData<>(HttpStatus.OK.value(), "Get leaderboard by class and part successfully!", responseDTO);
    }

    @GetMapping("/by-student/{studentId}")
    public ResponseData<List<QuizResultResponseDTO>> getLeaderboardByStudent(
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID studentId) {
        List<QuizResultResponseDTO> responseDTO = leaderboardService.getStudentIdOrderByCreatedAtDesc(studentId);
        return new ResponseData<>(HttpStatus.OK.value(), "Get leaderboard by student successfully!", responseDTO);
    }
}
