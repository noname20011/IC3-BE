package domain.system_study_api.dto.classroom;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Builder
@Setter
public class ClassroomRequestDTO implements Serializable {

    @NotBlank(message = "Field School Name must be not blank!")
    private String className;

}
