package domain.system_study_api.service.school;

import domain.system_study_api.dto.school.SchoolRequestDTO;
import domain.system_study_api.dto.school.SchoolResponseDTO;
import domain.system_study_api.entity.School;
import domain.system_study_api.mapper.SchoolMapper;
import domain.system_study_api.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;


    @Override
    public SchoolResponseDTO getSchoolById(UUID schoolId) {
        School school = schoolRepository.findByIdOrThrow(schoolId);
        return schoolMapper.mapToResponseDto(school);
    }

    @Override
    public List<SchoolResponseDTO> getAllSchools() {
        List<School> schools = schoolRepository.findAll();
        return schoolMapper.mapToListResponseDtos(schools);
    }

    @Override
    public SchoolResponseDTO addSchool(SchoolRequestDTO requestDTO) {
        School school = schoolMapper.mapToEntity(requestDTO);
        School saved = schoolRepository.save(school);
        return schoolMapper.mapToResponseDto(saved);
    }

    @Override
    public SchoolResponseDTO putSchool(UUID schoolId, SchoolRequestDTO requestDTO) {
        School school = schoolMapper.mapToEntity(requestDTO);
        School saved = schoolRepository.update(schoolId, school);
        log.info("Update school successfully!  {} ", saved);
        return schoolMapper.mapToResponseDto(saved);
    }

    @Override
    public void deleteSchool(UUID SchoolId) {
        schoolRepository.delete(SchoolId);
        log.info("Delete school successfully!  {} ", SchoolId);
    }
}
