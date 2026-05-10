package domain.system_study_api.dto.password_active;

import domain.system_study_api.helper.validators.uuid_validation.ValidUUID;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class AccessExamClientRequestDTO implements Serializable {

    private String passwordExam;

    private String fingerprintId;

    @NotBlank(message = "Field Secret Answer must be not blank!")
    private String secretAnswer;

    @ValidUUID(message = "Field Part Id dont match type!")
    private UUID partId; // Part for user want to access

    @ValidUUID(message = "Field Student Id dont match type!")
    private UUID studentId;
}
