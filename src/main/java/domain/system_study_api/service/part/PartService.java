package domain.system_study_api.service.part;

import domain.system_study_api.dto.part.PartRequestDTO;
import domain.system_study_api.dto.part.PartResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PartService {
    PartResponseDTO getPartById(UUID partId);
    List<PartResponseDTO> getAllParts(UUID levelId);
    PartResponseDTO addPart(PartRequestDTO requestDTO);
    PartResponseDTO putPart(UUID partId, PartRequestDTO requestDTO);
    void deletePart(UUID partId);
}
