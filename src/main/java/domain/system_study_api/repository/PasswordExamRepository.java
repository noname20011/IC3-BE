package domain.system_study_api.repository;

import domain.system_study_api.entity.Classroom;
import domain.system_study_api.entity.PasswordExam;
import domain.system_study_api.helper.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasswordExamRepository extends BaseRepository<PasswordExam, UUID> {
    Optional<PasswordExam> findByPassword(String password);
}
