package domain.system_study_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.system_study_api.entity.base.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_student", indexes = {
        @Index(name = "idx_external_id", columnList = "externalId")
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student extends BaseEntityHasId {


    private Integer externalId; // Lưu số STT từ Google Sheet

    private String firstName; // Cột C (TÊN)
    private String lastName;  // Cột B (HỌ)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<QuizResult> quizResults;

    @OneToOne(mappedBy = "student", cascade = CascadeType.REMOVE)
    private UserAccessExam userAccessExam;
}
