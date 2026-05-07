package domain.system_study_api.entity;

import domain.system_study_api.constants.RoleEnum;
import domain.system_study_api.entity.base.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntityHasId {

    @Column(name = "fullname", nullable = false, length = 50)
    private String fullName;

    @Column(name = "phone_number", unique = true, nullable = false, length = 11 )
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean active;

    @Column(name = "role", columnDefinition = "ENUM('ADMIN', 'CLIENT')")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
