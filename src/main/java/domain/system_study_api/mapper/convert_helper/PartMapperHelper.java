package domain.system_study_api.mapper.convert_helper;

import domain.system_study_api.entity.Level;
import domain.system_study_api.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PartMapperHelper {
    private final LevelRepository levelRepository;

    @Named("getLevelById")
    public Level getLevelById(UUID levelId) {
        return levelRepository.findByIdOrThrow(levelId);
    }
}
