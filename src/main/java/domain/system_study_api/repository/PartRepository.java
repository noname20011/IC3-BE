package domain.system_study_api.repository;

import domain.system_study_api.entity.Part;
import domain.system_study_api.helper.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PartRepository extends BaseRepository<Part, UUID> {
    List<Part> getPartsByLevelId(UUID levelId);
}
