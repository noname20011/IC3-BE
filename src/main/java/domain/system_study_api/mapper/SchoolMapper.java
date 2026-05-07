package domain.system_study_api.mapper;

import domain.system_study_api.dto.school.SchoolRequestDTO;
import domain.system_study_api.dto.school.SchoolResponseDTO;
import domain.system_study_api.entity.School;
import domain.system_study_api.helper.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SchoolMapper extends BaseMapper<School, SchoolRequestDTO, SchoolResponseDTO> {
}
