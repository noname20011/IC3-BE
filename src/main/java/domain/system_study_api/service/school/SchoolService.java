package domain.system_study_api.service.school;

import domain.system_study_api.dto.school.SchoolRequestDTO;
import domain.system_study_api.dto.school.SchoolResponseDTO;

import java.util.List;
import java.util.UUID;

public interface SchoolService {
    SchoolResponseDTO getSchoolById(UUID schoolId);
    List<SchoolResponseDTO> getAllSchools();
    SchoolResponseDTO addSchool(SchoolRequestDTO requestDTO);
    SchoolResponseDTO putSchool(UUID schoolId, SchoolRequestDTO requestDTO);
    void deleteSchool(UUID SchoolId);
}
