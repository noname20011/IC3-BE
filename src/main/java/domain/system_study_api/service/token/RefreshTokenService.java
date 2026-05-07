package domain.system_study_api.service.token;

import domain.system_study_api.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String username);
    Optional<RefreshToken> getRefreshToken(String refreshToken);
    RefreshToken verifyExpiration(RefreshToken refreshToken);
    void deleteRefreshToken(String token);
}
