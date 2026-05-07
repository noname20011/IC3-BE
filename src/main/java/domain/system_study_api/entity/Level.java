package domain.system_study_api.entity;

import domain.system_study_api.entity.base.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_level")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Level extends BaseEntityHasId {

    @Column(name = "name",  nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "level", cascade = CascadeType.REMOVE)
    private List<Part> parts;
}
