package domain.system_study_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.system_study_api.entity.base.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_device_registration")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceRegistration extends BaseEntityHasId {

    private String fingerprintId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_access_id", nullable = false)
    private UserAccessExam userAccessExam;


    @JsonFormat(pattern = "HH:mm dd/MM/yyyy")
    @Column(name = "next_session_at", nullable = false)
    private LocalDateTime nextSessionAt;
}
