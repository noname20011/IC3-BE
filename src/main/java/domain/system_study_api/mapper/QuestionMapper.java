package domain.system_study_api.mapper;

import domain.system_study_api.dto.question.QuestionRequestDTO;
import domain.system_study_api.dto.question.QuestionResponseDTO;
import domain.system_study_api.entity.Question;
import domain.system_study_api.helper.base.mapper.BaseMapper;
import domain.system_study_api.mapper.convert_helper.QuestionMapperHelper;
import domain.system_study_api.mapper.convert_helper.jsonToMapHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {QuestionMapperHelper.class, jsonToMapHelper.class})
public interface QuestionMapper extends BaseMapper<Question, QuestionRequestDTO, QuestionResponseDTO> {


    @Mapping(source = "partId", target = "part", qualifiedByName = "getPartById")
    @Mapping(target = "metadata", qualifiedByName = "jsonToMap")
    @Override
    Question mapToEntity(QuestionRequestDTO requestDTO);

    @Override
    @Mapping(source = "part.id", target = "partId")
    QuestionResponseDTO mapToResponseDto(Question part);

}
