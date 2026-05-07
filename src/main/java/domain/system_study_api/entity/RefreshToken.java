package domain.system_study_api.entity;

import domain.system_study_api.entity.base.BaseEntityHasId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "refresh_token")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends BaseEntityHasId {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDateInMs;
}
