package domain.system_study_api.dto.school;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class SchoolResponseDTO implements Serializable {

    private UUID id;
    private String schoolName;
    private String schoolCode;
    private String spreadsheetId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
