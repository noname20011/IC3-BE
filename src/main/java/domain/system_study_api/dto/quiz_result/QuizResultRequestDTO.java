package domain.system_study_api.dto.quiz_result;
import domain.system_study_api.helper.validators.uuid_validation.ValidUUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
@Setter
public class QuizResultRequestDTO implements Serializable {

    @ValidUUID(message = "Field Student Id must be not blank!")
    private UUID studentId;

    @NotNull(message = "Field score must be not null!")
    private Integer score;

    @NotNull(message = "Field time spent must be not null!")
    private Integer timeSpent;

    @NotBlank(message = "Field Student Name must be not blank!")
    private UUID partId;

}
