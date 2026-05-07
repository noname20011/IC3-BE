package domain.system_study_api.dto.auth;

import domain.system_study_api.dto.ResponseData;
import domain.system_study_api.dto.user.UserResponseDTO;
import lombok.Data;

@Data
public class AuthResponseDTO extends ResponseData<UserResponseDTO> {
    private String accessToken;

    public AuthResponseDTO(int status, String message, UserResponseDTO data, String accessToken) {
        super(status, message, data);
        this.accessToken = accessToken;
    }
}
