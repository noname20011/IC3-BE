package domain.system_study_api.dto.part;
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
public class PartRequestDTO implements Serializable {

    @NotBlank(message = "Field Name must be not blank!")
    private String name;

    @NotBlank(message = "Field Description must be not blank!")
    private String description;

    @NotNull(message = "Field Sort Order must be not null!")
    private Integer sortOrder;

    @NotNull(message = "Field Duration must be not null!")
    private Integer duration;

    @ValidUUID(message = "Not match UUID type!")
    private UUID levelId;
}
