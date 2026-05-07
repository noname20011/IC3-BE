package domain.system_study_api.dto.level;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Builder
@Setter
public class LevelRequestDTO implements Serializable {

    @NotBlank(message = "Field Name must be not blank!")
    private String name;

    @NotBlank(message = "Field Description must be not blank!")
    private String description;

}
