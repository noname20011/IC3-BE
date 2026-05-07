package domain.system_study_api.mapper.convert_helper;

import domain.system_study_api.entity.Part;
import domain.system_study_api.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class QuestionMapperHelper {
    private final PartRepository partRepository;

    @Named("getPartById")
    public Part getLevelById(UUID partId) {
        return partRepository.findByIdOrThrow(partId);
    }
}
