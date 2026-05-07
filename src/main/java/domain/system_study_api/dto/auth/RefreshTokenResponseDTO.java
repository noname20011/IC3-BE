package domain.system_study_api.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshTokenResponseDTO {
    private String newAccessToken;
}
