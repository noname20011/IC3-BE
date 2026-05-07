package domain.system_study_api.repository;

import domain.system_study_api.entity.DeviceRegistration;
import domain.system_study_api.helper.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRegistrationRepository extends BaseRepository<DeviceRegistration, UUID> {
    DeviceRegistration findByUserAccessExamId(UUID accessId);
}
