package domain.system_study_api.repository;

import domain.system_study_api.entity.Classroom;
import domain.system_study_api.entity.School;
import domain.system_study_api.helper.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassroomRepository extends BaseRepository<Classroom, UUID> {
    // Kiểm tra lớp đã tồn tại trong trường chưa để tránh trùng
    boolean existsBySchoolAndName(School school, String className);
    List<Classroom> findBySchoolId(UUID schoolId);

    List<Classroom> findBySchoolIdOrderByNameAsc(UUID schoolId);
}
