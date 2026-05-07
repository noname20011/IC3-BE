package domain.system_study_api.repository;

import domain.system_study_api.entity.User;
import domain.system_study_api.helper.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPassword(String password);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
