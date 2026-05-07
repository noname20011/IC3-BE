package domain.system_study_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import domain.system_study_api.entity.base.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_classroom")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Classroom extends BaseEntityHasId {

    @Column(nullable = false)
    private String className; // Ví dụ: "6a1", "6a2"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id",  nullable = false)
    private School school; // Thuộc về trường nào

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Student> students; // Danh sách học sinh trong lớp này
}
