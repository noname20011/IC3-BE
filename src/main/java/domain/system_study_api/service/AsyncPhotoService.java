package domain.system_study_api.service;

import domain.system_study_api.dto.FilePhotoDTO;
import domain.system_study_api.service.cloudinary.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AsyncPhotoService {

    private final CloudinaryService cloudinaryService;

    @Async("uploadPhotoExecutor")
    public CompletableFuture<String> uploadPhoto(MultipartFile requestDTO) {
        String url = cloudinaryService.uploadPhoto(requestDTO);
        return CompletableFuture.completedFuture(url);
    }

    @Async("deletePhotoExecutor")
    public void deletePhoto(String url) {
        cloudinaryService.deleteDestinationPhoto(url);
        CompletableFuture.completedFuture(null);
    }
}
