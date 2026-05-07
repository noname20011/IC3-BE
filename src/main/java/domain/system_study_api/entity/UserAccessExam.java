package domain.system_study_api.entity;

import domain.system_study_api.entity.base.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_user_access_exam")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAccessExam extends BaseEntityHasId {

    // Hash secret answer
    @Column(name = "secret_answer")
    private String secretAnswer;

    @OneToOne(mappedBy = "userAccessExam", cascade = CascadeType.REMOVE)
    private DeviceRegistration device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "password_exam_id",  nullable = false)
    private PasswordExam passwordExam;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id",  nullable = false)
    private Student student;
}


