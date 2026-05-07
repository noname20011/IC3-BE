package domain.system_study_api.mapper;

import domain.system_study_api.dto.classroom.ClassroomRequestDTO;
import domain.system_study_api.dto.classroom.ClassroomResponseDTO;
import domain.system_study_api.entity.Classroom;
import domain.system_study_api.helper.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClassroomMapper extends BaseMapper<Classroom, ClassroomRequestDTO, ClassroomResponseDTO> {
}
