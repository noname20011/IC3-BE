package domain.system_study_api.dto.user;

import domain.system_study_api.constants.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class UserResponseDTO implements Serializable {

    private UUID id;
    private String fullName;
    private String phoneNumber;
    private Boolean active;
    private RoleEnum role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
