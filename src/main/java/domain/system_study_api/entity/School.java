package domain.system_study_api.entity;

import domain.system_study_api.entity.base.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_school")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class School extends BaseEntityHasId {

    @Column(unique = true, nullable = false)
    private String schoolCode;

    private String schoolName;

    @Column(nullable = false)
    private String spreadsheetId;

    @OneToMany(mappedBy = "school", cascade = CascadeType.REMOVE)
    private List<Classroom> classrooms;
}
