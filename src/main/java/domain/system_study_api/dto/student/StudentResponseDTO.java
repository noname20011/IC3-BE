package domain.system_study_api.dto.student;

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
public class StudentResponseDTO implements Serializable {

    private UUID id;
    private Integer externalId;
    private String name;
    private String schoolName;
    private String className;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
