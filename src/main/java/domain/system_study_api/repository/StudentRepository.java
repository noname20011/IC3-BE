package domain.system_study_api.repository;

import domain.system_study_api.entity.Classroom;
import domain.system_study_api.entity.Student;
import domain.system_study_api.helper.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends BaseRepository<Student, UUID> {
    // Lấy danh sách học sinh theo lớp để đối soát
    List<Student> findByClassroomIdOrderByExternalIdAsc(UUID classroomId);

    // Tìm nhanh học sinh trong một lớp theo STT
    Student findByClassroomAndExternalId(Classroom classroom, Integer externalId);

}
