package domain.system_study_api.repository;

import domain.system_study_api.dto.quiz_result.TopStudentDTO;
import domain.system_study_api.entity.QuizResult;
import domain.system_study_api.helper.base.repository.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LeaderBoardRepository extends BaseRepository<QuizResult, UUID> {

    // Tìm tất cả kết quả của một học sinh
    List<QuizResult> findByStudentIdOrderByCreatedAtDesc(UUID studentId);

    // Lấy danh sách kết quả cao nhất của một lớp để làm Leaderboard
    @Query("SELECT r FROM QuizResult r WHERE r.student.classroom.id = :classId " +
            "AND r.part.id = :partId ORDER BY r.score DESC, r.timeSpent ASC")
    List<QuizResult> findLeaderboardByClassAndPart(UUID classId, UUID partId);

    // Lấy danh sách kết quả cao nhất của một lớp để làm Leaderboard
    @Query("""
        SELECT 
            r.id as id,
            r.score as score,
            r.timeSpent as time_spent,
            s.firstName as first_name,
            s.lastName as last_name,
            c.name as class_name,
            sch.name as school_name,
            p.name as part_name,
            p.level.name as level_name
        FROM QuizResult r
        JOIN r.student s
        JOIN s.classroom c
        JOIN c.school sch
        JOIN r.part p
        WHERE r.part.id = :partId
        ORDER BY r.score DESC, r.timeSpent ASC
    """)
    List<TopStudentDTO> findTop20ByPartId( @Param("partId") UUID partId, Pageable pageable);

    @Query(value = "SELECT * FROM ( SELECT qr.score, qr.time_spent, l.name as level_name, \n" +
                   "st.first_name, st.last_name, cl.name, sc.name, ROW_NUMBER() OVER \n" +
                   "(PARTITION BY p.level_id ORDER BY qr.score DESC, qr.time_spent ASC) as rn \n" +
                   "FROM tbl_quiz_result qr \n" +
                   "JOIN tbl_part p ON qr.part_id = p.id \n" +
                   "JOIN tbl_level l ON p.level_id = l.id \n" +
                   "JOIN tbl_student st ON qr.student_id = st.id \n" +
                   "JOIN tbl_classroom cl on cl.id = st.classroom_id\n" +
                   "JOIN tbl_school sc on sc.id = cl.school_id) as ranked_results WHERE rn = 1;\n",
            nativeQuery = true)
    List<TopStudentDTO> findTopStudentForEachLevel();

    @Query("SELECT r.id as id, r.score as score, r.timeSpent as time_spent, " +
            "s.firstName as first_name, s.lastName as last_name, c.name as class_name, " +
            "sch.name as school_name, p.name as part_name, p.level.name as level_name " +
            "FROM QuizResult r " +
            "JOIN r.student s " +
            "JOIN s.classroom c " +
            "JOIN c.school sch " +
            "JOIN r.part p " +
            "WHERE r.part.id = :partId " +
            "AND s.classroom.id = :classId " +
            "ORDER BY r.score DESC, r.timeSpent ASC")
    List<TopStudentDTO> findByClassIdAndPartId(
            @Param("partId") UUID partId,
            @Param("classId") UUID classId
    );
}
