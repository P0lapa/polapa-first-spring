package com.example.first_spring_project.Mappers;

import com.example.first_spring_project.DTOs.AdvertisementCreateDto;
import com.example.first_spring_project.DTOs.AdvertisementDto;
import com.example.first_spring_project.Entities.AdvertisementEntity;
import com.example.first_spring_project.Entities.PhotoEntity;

import java.util.stream.Collectors;

public class AdvertisementMapper {

    public static AdvertisementDto toDto(AdvertisementEntity advertisementEntity){
        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setId(advertisementEntity.getId());
        advertisementDto.setTitle(advertisementEntity.getTitle());
        advertisementDto.setDescription(advertisementEntity.getDescription());
        advertisementDto.setPhone(advertisementEntity.getPhone());
        advertisementDto.setCategory(advertisementEntity.getCategory());
        advertisementDto.setCreatedAt(advertisementEntity.getCreatedAt());
        advertisementDto.setPhotoUrls(advertisementEntity.getPhotos().stream().map(PhotoEntity::getUrl).collect(Collectors.toList()));

        return advertisementDto;
    }

    public static AdvertisementEntity fromCreateDto(AdvertisementCreateDto advertisementCreateDto){
        AdvertisementEntity advertisementEntity = new AdvertisementEntity();

        advertisementEntity.setTitle(advertisementCreateDto.getTitle());
        advertisementEntity.setDescription(advertisementCreateDto.getDescription());
        advertisementEntity.setPhone(advertisementCreateDto.getPhone());
        advertisementEntity.setCategory(advertisementCreateDto.getCategory());

        return advertisementEntity;
    }
}
