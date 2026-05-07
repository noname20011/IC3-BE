package domain.system_study_api.repository;

import domain.system_study_api.entity.Level;
import domain.system_study_api.helper.base.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LevelRepository extends BaseRepository<Level, UUID> {

    List<Level> findAllByOrderByNameAsc();
}
