package domain.system_study_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import domain.system_study_api.entity.base.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_part")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Part extends BaseEntityHasId {

    @Column(name = "name",  nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id",  nullable = false)
    private Level level;

    @Column(name = "duration")
    private Integer duration;

    @OneToMany(mappedBy = "part" )
    private List<QuizResult> quiz_results;

    @OneToMany(mappedBy = "part" )
    private List<Question> questions;
}
