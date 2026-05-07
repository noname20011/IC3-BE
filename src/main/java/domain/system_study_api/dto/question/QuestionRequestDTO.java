package domain.system_study_api.dto.question;

import domain.system_study_api.constants.QuestionTypeEnum;
import domain.system_study_api.helper.validators.enum_validation.EnumPattern;
import domain.system_study_api.helper.validators.uuid_validation.ValidUUID;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
@Setter
public class QuestionRequestDTO implements Serializable {

    @NotBlank(message = "Field Text must be not blank!")
    private String text;

    @EnumPattern(name="Question Type", enumClass = QuestionTypeEnum.class)
    private QuestionTypeEnum type;

    private MultipartFile imageFile;
    private String imageUrl;


    @NotBlank(message = "Field metadata must be not blank!")
    private String metadata;

    @ValidUUID(message = "Not match UUID type!")
    private UUID partId;
}
