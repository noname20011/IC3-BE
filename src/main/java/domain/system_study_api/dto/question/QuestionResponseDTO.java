package domain.system_study_api.dto.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class QuestionResponseDTO implements Serializable {
    private UUID id;
    private String type;
    private String imageUrl;
    private String text;
    private Map<String, Object> metadata;
    private UUID partId;
}
