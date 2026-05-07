package domain.system_study_api.repository;

import domain.system_study_api.entity.QuizResult;
import domain.system_study_api.helper.base.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuizSubmitRepository extends BaseRepository<QuizResult, UUID> {

    @Query("SELECT r FROM QuizResult r " +
            "JOIN FETCH r.student s " +
            "JOIN FETCH s.classroom c " +
            "JOIN FETCH c.school sch " +
            "JOIN FETCH r.part p " + // Lấy luôn Part để có sortOrder
            "WHERE r.id = :id")
    Optional<QuizResult> findByIdWithDetails(@Param("id") UUID id);
}
