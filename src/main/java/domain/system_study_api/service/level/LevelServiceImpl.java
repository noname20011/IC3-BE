package domain.system_study_api.service.level;

import domain.system_study_api.dto.level.LevelRequestDTO;
import domain.system_study_api.dto.level.LevelResponseDTO;
import domain.system_study_api.entity.Level;
import domain.system_study_api.mapper.LevelMapper;
import domain.system_study_api.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;


    @Override
    public LevelResponseDTO getLevelById(UUID levelId) {
        Level data = levelRepository.findByIdOrThrow(levelId);
        return levelMapper.mapToResponseDto(data);
    }

    @Override
    public List<LevelResponseDTO> getAllLevels() {
        List<Level> data = levelRepository.findAllByOrderByNameAsc();
        return levelMapper.mapToListResponseDtos(data);
    }

    @Override
    public LevelResponseDTO addLevel(LevelRequestDTO requestDTO) {
        Level entity = levelMapper.mapToEntity(requestDTO);
        Level saved = levelRepository.save(entity);
        return levelMapper.mapToResponseDto(saved);
    }

    @Override
    public LevelResponseDTO putLevel(UUID levelId, LevelRequestDTO requestDTO) {
        Level entity = levelMapper.mapToEntity(requestDTO);
        Level saved = levelRepository.update(levelId, entity);
        return levelMapper.mapToResponseDto(saved);
    }

    @Override
    public void deleteLevel(UUID levelId) {
        levelRepository.delete(levelId);
    }
}
