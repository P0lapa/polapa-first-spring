package com.example.first_spring_project.service;

import com.example.first_spring_project.dto.CreateAdvertisementRequest;
import com.example.first_spring_project.dto.AdvertisementDto;
import com.example.first_spring_project.entity.AdvertisementEntity;
import com.example.first_spring_project.entity.PhotoEntity;
import com.example.first_spring_project.mapper.AdvertisementMapper;
import com.example.first_spring_project.repository.AdvertisementRepository;
import com.example.first_spring_project.repository.PhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final PhotoRepository photoRepository;
    private final AdvertisementMapper advertisementMapper;

    public List<AdvertisementDto> getAllAdvertisements() {
        return advertisementRepository.findAll().stream().map(advertisementMapper::toDto).collect(Collectors.toList());
    }

    public AdvertisementDto getAdvertisementById(UUID id) {
        AdvertisementEntity advertisementEntity = advertisementRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Объявление не найдено"));
        return advertisementMapper.toDto(advertisementEntity);
    }

    public AdvertisementDto createAdvertisement(CreateAdvertisementRequest createAdvertisementRequest) {
        AdvertisementEntity advertisementEntity = advertisementMapper.fromCreateDto(createAdvertisementRequest);

        List<PhotoEntity> photos = createAdvertisementRequest.getPhotos().stream()
                .map(photoDto -> PhotoEntity.builder()
                        .url(photoDto.getUrl())
                        .originalFilename(photoDto.getOriginalFilename())
                        .advertisementId(advertisementEntity)
                        .build())
                .collect(Collectors.toList());

        advertisementEntity.setPhotos(photos);

        AdvertisementEntity createdAdvertisementEntity = advertisementRepository.save(advertisementEntity);
        return advertisementMapper.toDto(createdAdvertisementEntity);
    }

    public AdvertisementDto updateAdvertisement(UUID id, CreateAdvertisementRequest advertisementUpdateDto) {
        AdvertisementEntity advertisementEntity = advertisementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));

        advertisementEntity.setTitle(advertisementUpdateDto.getTitle());
        advertisementEntity.setDescription(advertisementUpdateDto.getDescription());
        advertisementEntity.setPhone(advertisementUpdateDto.getPhone());
        advertisementEntity.setCategory(advertisementUpdateDto.getCategory());

        advertisementEntity.getPhotos().clear();

        List<PhotoEntity> newPhotos = advertisementUpdateDto.getPhotos().stream()
                .map(photoDto -> PhotoEntity.builder()
                        .url(photoDto.getUrl())
                        .originalFilename(photoDto.getOriginalFilename())
                        .advertisementId(advertisementEntity)
                        .build())
                .toList();

        advertisementEntity.getPhotos().addAll(newPhotos);

        AdvertisementEntity updatedAdvertisementEntity = advertisementRepository.save(advertisementEntity);

        return advertisementMapper.toDto(updatedAdvertisementEntity);
    }

     public void deleteAdvertisement(UUID id) {
        advertisementRepository.deleteById(id);
     }
}
