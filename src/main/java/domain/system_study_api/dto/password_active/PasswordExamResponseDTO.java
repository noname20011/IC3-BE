package domain.system_study_api.dto.password_active;

import domain.system_study_api.constants.PasswordStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class PasswordExamResponseDTO implements Serializable {

    private UUID id;
    private PasswordStatus status;
    private LocalDate expireDate;
}
