package com.example.first_spring_project.service;

import com.example.first_spring_project.dto.PhotoResponse;
import com.example.first_spring_project.entity.PhotoEntity;
import com.example.first_spring_project.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    private final Path uploadPath = Paths.get("src/main/resources/uploads");

    public List<PhotoResponse> saveAll(List<MultipartFile> files) throws IOException {
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        List<PhotoEntity> photoEntities = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String generatedName = UUID.randomUUID() + extension;

            Path filePath = uploadPath.resolve(generatedName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String url = "/uploads/" + generatedName;

            photoEntities.add(PhotoEntity.builder()
                    .url(url)
                    .originalFilename(originalFilename)
                    .build());
        }

        List<PhotoEntity> saved = photoRepository.saveAll(photoEntities);

        return saved.stream()
                .map(p -> PhotoResponse.builder()
                        .url(p.getUrl())
                        .originalFilename(p.getOriginalFilename())
                        .build())
                .toList();
    }


}
