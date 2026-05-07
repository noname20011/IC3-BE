package domain.system_study_api.entity;

import domain.system_study_api.entity.base.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_quiz_result")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuizResult extends BaseEntityHasId {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id",  nullable = false)
    private Student student;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id", nullable = false)
    private Part part; // 1: OT1, 2: OT2, 3: OT3

    private Integer score;

    private Integer timeSpent;

    // Helper method để lấy Spreadsheet ID nhanh từ kết quả
    public String getTargetSpreadsheetId() {
        return this.student.getClassroom().getSchool().getSpreadsheetId();
    }
}
