package com.example.first_spring_project.Services;

import com.example.first_spring_project.DTOs.AdvertisementCreateDto;
import com.example.first_spring_project.DTOs.AdvertisementDto;
import com.example.first_spring_project.Entities.AdvertisementEntity;
import com.example.first_spring_project.Entities.PhotoEntity;
import com.example.first_spring_project.Mappers.AdvertisementMapper;
import com.example.first_spring_project.Repositories.AdvertisementRepository;
import com.example.first_spring_project.Repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
        private final PhotoRepository photoRepository;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository, PhotoRepository photoRepository) {
        this.advertisementRepository = advertisementRepository;
        this.photoRepository = photoRepository;
    }

    public List<AdvertisementDto> getAllAdvertisements() {
        return advertisementRepository.findAll().stream().map(AdvertisementMapper::toDto).collect(Collectors.toList());
    }

    public AdvertisementDto getAdvertisementById(UUID id) {
        AdvertisementEntity advertisementEntity = advertisementRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Объявление не найдено"));
        return AdvertisementMapper.toDto(advertisementEntity);
    }

    public AdvertisementDto createAdvertisement(AdvertisementCreateDto advertisementCreateDto) {
        AdvertisementEntity advertisementEntity = AdvertisementMapper.fromCreateDto(advertisementCreateDto);

        List<PhotoEntity> photos = advertisementCreateDto.getPhotoUrls().stream()
                .map(url -> PhotoEntity.builder()
                        .url(url)
                        .advertisementId(advertisementEntity)
                        .build())
                .collect(Collectors.toList());

        advertisementEntity.setPhotos(photos);

        AdvertisementEntity createdAdvertisementEntity = advertisementRepository.save(advertisementEntity);
        return AdvertisementMapper.toDto(createdAdvertisementEntity);
    }

    public AdvertisementDto updateAdvertisement(UUID id, AdvertisementCreateDto advertisementUpdateDto) {
        AdvertisementEntity advertisementEntity = advertisementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));

        advertisementEntity.setTitle(advertisementUpdateDto.getTitle());
        advertisementEntity.setDescription(advertisementUpdateDto.getDescription());
        advertisementEntity.setPhone(advertisementUpdateDto.getPhone());
        advertisementEntity.setCategory(advertisementUpdateDto.getCategory());

        advertisementEntity.getPhotos().clear();

        List<PhotoEntity> newPhotos = advertisementUpdateDto.getPhotoUrls().stream()
                .map(url -> PhotoEntity.builder()
                        .url(url)
                        .advertisementId(advertisementEntity)
                        .build())
                .toList();

        advertisementEntity.getPhotos().addAll(newPhotos);

        AdvertisementEntity updatedAdvertisementEntity = advertisementRepository.save(advertisementEntity);

        return AdvertisementMapper.toDto(updatedAdvertisementEntity);
    }

     public void deleteAdvertisement(UUID id) {
        advertisementRepository.deleteById(id);
     }
}
