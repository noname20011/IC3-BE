package domain.system_study_api.mapper;

import domain.system_study_api.dto.level.LevelRequestDTO;
import domain.system_study_api.dto.level.LevelResponseDTO;
import domain.system_study_api.entity.Level;
import domain.system_study_api.helper.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LevelMapper extends BaseMapper<Level, LevelRequestDTO, LevelResponseDTO> {
}
