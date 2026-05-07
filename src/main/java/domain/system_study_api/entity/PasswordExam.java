package domain.system_study_api.entity;

import domain.system_study_api.constants.PasswordStatus;
import domain.system_study_api.entity.base.BaseEntityHasId;
import domain.system_study_api.helper.utils.JsonToMapConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "tbl_password_exam")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasswordExam extends BaseEntityHasId {

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PasswordStatus status;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "allow_all", columnDefinition = "BOOLEAN")
    private Boolean allowedAll = false;

    @Column(name = "allow_skip_identify", columnDefinition = "BOOLEAN")
    private Boolean allowedSkipIdentify = false;

//    @Type(JsonType.class)
    @Column(columnDefinition = "LONGTEXT")
    @Convert(converter = JsonToMapConverter.class)
    private Map<String, Object> metadata;

    @OneToMany(mappedBy = "passwordExam", cascade = CascadeType.REMOVE)
    private List<UserAccessExam> userAccessExams;
}
