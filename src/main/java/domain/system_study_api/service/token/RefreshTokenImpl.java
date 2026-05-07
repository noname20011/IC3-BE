package domain.system_study_api.service.token;

import domain.system_study_api.entity.RefreshToken;
import domain.system_study_api.exception.DisabledException;
import domain.system_study_api.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refreshExpiration}")
    private long refreshExpirationInMs;

    @Override
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder().username(username)
                .token(UUID.randomUUID().toString())
                .expiryDateInMs(Instant.now().plusMillis(refreshExpirationInMs))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> getRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if(token.getExpiryDateInMs().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new DisabledException("Refresh token is expired");
        }
        return token;
    }

    @Override
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
        log.info("Refresh token has been deleted!");
    }
}
