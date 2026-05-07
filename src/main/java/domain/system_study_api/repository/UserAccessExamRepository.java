package domain.system_study_api.repository;

import domain.system_study_api.entity.UserAccessExam;
import domain.system_study_api.helper.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAccessExamRepository extends BaseRepository<UserAccessExam, UUID> {
    Optional<UserAccessExam> findUserAccessExamByStudentId(UUID studentId);
}
