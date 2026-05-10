package domain.system_study_api.dto.school;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Builder
@Setter
public class SchoolRequestDTO implements Serializable {

    @NotBlank(message = "Field School Name must be not blank!")
    private String name;

    @NotBlank(message = "Field School Code must be not blank!")
    private String schoolCode;

    @NotBlank(message = "Field Spreadsheet Id Code must be not blank!")
    private String spreadsheetId;

}
