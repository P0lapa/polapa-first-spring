package com.example.first_spring_project.service;

import com.example.first_spring_project.dto.CreateAdvertisementRequest;
import com.example.first_spring_project.dto.AdvertisementDto;
import com.example.first_spring_project.entity.AdvertisementEntity;
import com.example.first_spring_project.entity.CategoryEntity;
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

        advertisementEntity.getCategories().forEach(cat -> cat.setAdvertisementId(advertisementEntity));

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

    public AdvertisementDto updateAdvertisement(UUID id, CreateAdvertisementRequest request) {
        AdvertisementEntity advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));

        advertisement.setTitle(request.getTitle());
        advertisement.setDescription(request.getDescription());
        advertisement.setPhone(request.getPhone());

        advertisement.getPhotos().clear();

        List<PhotoEntity> newPhotos = request.getPhotos().stream()
                .map(photoDto -> PhotoEntity.builder()
                        .url(photoDto.getUrl())
                        .originalFilename(photoDto.getOriginalFilename())
                        .advertisementId(advertisement)
                        .build())
                .toList();

        advertisement.getPhotos().addAll(newPhotos);

        advertisement.getCategories().clear();

        List<CategoryEntity> newCategories = request.getCategories().stream()
                .map(name -> CategoryEntity.builder()
                        .name(name)
                        .advertisementId(advertisement)
                        .build())
                .toList();

        advertisement.getCategories().addAll(newCategories);

        AdvertisementEntity updated = advertisementRepository.save(advertisement);
        return advertisementMapper.toDto(updated);
    }


    public void deleteAdvertisement(UUID id) {
        advertisementRepository.deleteById(id);
     }
}
