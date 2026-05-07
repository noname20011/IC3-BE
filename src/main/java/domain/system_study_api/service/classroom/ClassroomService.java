package domain.system_study_api.service.classroom;

import domain.system_study_api.dto.classroom.ClassroomResponseDTO;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ClassroomService {
    void scanAndUpdateClassrooms(UUID schoolId) throws IOException;

    List<ClassroomResponseDTO> getClassrooms(UUID schoolId);
}
