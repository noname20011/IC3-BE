package domain.system_study_api.mapper.convert_helper;

import domain.system_study_api.entity.PasswordExam;
import domain.system_study_api.repository.PasswordExamRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PasswordExamMapperHelper {
    private final PasswordExamRepository repository;

    @Named("getPartById")
    public PasswordExam getPasswordExamById(UUID passwordExamId) {
        return repository.findByIdOrThrow(passwordExamId);
    }
}
