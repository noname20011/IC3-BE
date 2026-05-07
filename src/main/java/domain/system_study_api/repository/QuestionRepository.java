package domain.system_study_api.repository;

import domain.system_study_api.entity.Question;
import domain.system_study_api.helper.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends BaseRepository<Question, UUID> {

    List<Question> findAllByPartId(UUID partId);
}
