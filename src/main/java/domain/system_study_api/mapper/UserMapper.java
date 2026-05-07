package domain.system_study_api.mapper;

import domain.system_study_api.dto.user.UserRequestDTO;
import domain.system_study_api.dto.user.UserResponseDTO;
import domain.system_study_api.entity.User;
import domain.system_study_api.helper.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends BaseMapper<User, UserRequestDTO, UserResponseDTO> {
}
