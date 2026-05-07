package domain.system_study_api.mapper;

import domain.system_study_api.dto.part.PartRequestDTO;
import domain.system_study_api.dto.part.PartResponseDTO;
import domain.system_study_api.entity.Part;
import domain.system_study_api.helper.base.mapper.BaseMapper;
import domain.system_study_api.mapper.convert_helper.PartMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = PartMapperHelper.class)
public interface PartMapper extends BaseMapper<Part, PartRequestDTO, PartResponseDTO> {


    @Mapping(source = "levelId", target = "level", qualifiedByName = "getLevelById")
    @Override
    Part mapToEntity(PartRequestDTO requestDTO);

    @Override
    @Mapping(target = "totalQuestions", expression = "java(part.getQuestions() != null ? part.getQuestions().size() : 0)")
    PartResponseDTO mapToResponseDto(Part part);
}
