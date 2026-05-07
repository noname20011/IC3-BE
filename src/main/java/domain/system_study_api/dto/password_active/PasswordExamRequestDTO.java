package domain.system_study_api.dto.password_active;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Builder
@Setter
public class PasswordExamRequestDTO implements Serializable {

    @NotBlank(message = "Field password must be not blank!")
    private String password;

    @NotBlank(message = "Field Expire Date must be not null!")
    private LocalDate expireDate;

    private Boolean allowedAll;
    private Boolean allowedSkipIdentify;

    @NotBlank(message = "Field metadata must be not blank!")
    private String metadata;
}
