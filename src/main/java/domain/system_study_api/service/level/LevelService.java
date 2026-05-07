package domain.system_study_api.service.level;

import domain.system_study_api.dto.level.LevelRequestDTO;
import domain.system_study_api.dto.level.LevelResponseDTO;

import java.util.List;
import java.util.UUID;

public interface LevelService {
    LevelResponseDTO getLevelById(UUID levelId);
    List<LevelResponseDTO> getAllLevels();
    LevelResponseDTO addLevel(LevelRequestDTO requestDTO);
    LevelResponseDTO putLevel(UUID levelId, LevelRequestDTO requestDTO);
    void deleteLevel(UUID levelId);
}
