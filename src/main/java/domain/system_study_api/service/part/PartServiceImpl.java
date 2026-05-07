package domain.system_study_api.service.part;

import domain.system_study_api.dto.part.PartRequestDTO;
import domain.system_study_api.dto.part.PartResponseDTO;
import domain.system_study_api.entity.Part;
import domain.system_study_api.mapper.PartMapper;
import domain.system_study_api.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {

    private final PartRepository repository;
    private final PartMapper partMapper;


    @Override
    public PartResponseDTO getPartById(UUID partId) {
        Part part = repository.findByIdOrThrow(partId);
        return partMapper.mapToResponseDto(part);
    }

    @Override
    public List<PartResponseDTO> getAllParts(UUID levelId) {
        List<Part> parts = repository.getPartsByLevelId(levelId);
        return partMapper.mapToListResponseDtos(parts);
    }

    @Override
    public PartResponseDTO addPart(PartRequestDTO requestDTO) {
        Part entity = partMapper.mapToEntity(requestDTO);
        Part saved = repository.save(entity);
        return partMapper.mapToResponseDto(saved);
    }

    @Override
    public PartResponseDTO putPart(UUID partId, PartRequestDTO requestDTO) {
        Part entity = partMapper.mapToEntity(requestDTO);
        Part saved = repository.update(partId, entity);
        return partMapper.mapToResponseDto(saved);
    }

    @Override
    public void deletePart(UUID partId) {
        repository.delete(partId);
    }
}
