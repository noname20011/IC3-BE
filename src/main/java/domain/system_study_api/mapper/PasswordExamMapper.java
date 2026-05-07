package domain.system_study_api.mapper;

import domain.system_study_api.dto.password_active.PasswordExamRequestDTO;
import domain.system_study_api.dto.password_active.PasswordExamResponseDTO;
import domain.system_study_api.entity.PasswordExam;
import domain.system_study_api.helper.base.mapper.BaseMapper;
import domain.system_study_api.mapper.convert_helper.PasswordExamMapperHelper;
import domain.system_study_api.mapper.convert_helper.jsonToMapHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {PasswordExamMapperHelper.class, jsonToMapHelper.class})
public interface PasswordExamMapper extends BaseMapper<PasswordExam, PasswordExamRequestDTO, PasswordExamResponseDTO> {

    @Mapping(target = "metadata", qualifiedByName = "jsonToMap")
    @Override
    PasswordExam mapToEntity(PasswordExamRequestDTO requestDTO);

    @Override
    PasswordExamResponseDTO mapToResponseDto(PasswordExam part);
}
