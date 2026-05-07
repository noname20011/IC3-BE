package domain.system_study_api.repository;

import domain.system_study_api.entity.School;
import domain.system_study_api.helper.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SchoolRepository extends BaseRepository<School, UUID> {
}
