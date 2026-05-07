package domain.system_study_api.dto.quiz_result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class QuizResultResponseDTO implements Serializable {

    private UUID id;
    private String studentName;
    private Integer score;
    private Integer timeSpent;
    private String schoolName;
    private String className;
    private String levelName;
    private String partName;
}
