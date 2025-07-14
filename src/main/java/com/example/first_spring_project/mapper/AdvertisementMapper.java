package com.example.first_spring_project.mapper;

import com.example.first_spring_project.dto.CreateAdvertisementRequest;
import com.example.first_spring_project.dto.AdvertisementDto;
import com.example.first_spring_project.dto.PhotoResponse;
import com.example.first_spring_project.entity.AdvertisementEntity;
import com.example.first_spring_project.entity.PhotoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdvertisementMapper {

    AdvertisementDto toDto(AdvertisementEntity advertisementEntity);

    AdvertisementEntity fromCreateDto(CreateAdvertisementRequest createAdvertisementRequest);

    List<PhotoResponse> map(List<PhotoEntity> photos);
}
