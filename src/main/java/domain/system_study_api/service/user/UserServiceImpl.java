package domain.system_study_api.service.user;

import domain.system_study_api.dto.user.UserRequestDTO;
import domain.system_study_api.dto.user.UserResponseDTO;
import domain.system_study_api.entity.User;
import domain.system_study_api.exception.DuplicateResourceException;
import domain.system_study_api.exception.NotFoundException;
import domain.system_study_api.mapper.UserMapper;
import domain.system_study_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    //    Begin Methods
    @Override
    public UserResponseDTO findUserByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundException("User not found!"));

        return userMapper.mapToResponseDto(user);
    }

    @Override
    public UserResponseDTO getUserById(UUID userId) {
        User user = userRepository.findByIdOrThrow(userId);
        log.info("getUserById {} ", user.getId());
        return userMapper.mapToResponseDto(user);
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByPhoneNumber(userRequestDTO.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists");
        }

        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        User user = userMapper.mapToEntity(userRequestDTO);
        User saved = userRepository.save(user);
        UserResponseDTO userResponseDTO = userMapper.mapToResponseDto(saved);

        log.info("Add user successfully!  {} ", userResponseDTO);
        return userResponseDTO;
    }

    @Override
    public User registerUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByPhoneNumber(userRequestDTO.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists");
        }

        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        User user = userMapper.mapToEntity(userRequestDTO);

        log.info("Register successfully! ");

        return userRepository.save(user);
    }

    @Override
    public UserResponseDTO putUser(UUID userId, UserRequestDTO userRequestDTO) {
        User saved = userRepository.update(userId, userMapper.mapToEntity(userRequestDTO));
        UserResponseDTO userResponseDTO = userMapper.mapToResponseDto(saved);

        log.info("Register user successfully!  {} ", userResponseDTO);
        return userResponseDTO;
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.delete(userId);
        log.info("Delete user successfully!  {} ", userId);
    }
}
