package domain.system_study_api.service.user;

import domain.system_study_api.dto.user.UserRequestDTO;
import domain.system_study_api.dto.user.UserResponseDTO;
import domain.system_study_api.entity.User;

import java.util.UUID;

public interface UserService {
    UserResponseDTO findUserByPhoneNumber(String phoneNumber);
    UserResponseDTO getUserById(UUID userId);
    UserResponseDTO addUser(UserRequestDTO userRequestDTO);
    User registerUser(UserRequestDTO userRequestDTO);
    UserResponseDTO putUser(UUID userId, UserRequestDTO userRequestDTO);
    void deleteUser(UUID userId);
}
