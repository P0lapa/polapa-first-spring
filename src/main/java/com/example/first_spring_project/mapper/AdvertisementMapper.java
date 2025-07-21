package com.example.first_spring_project.mapper;

import com.example.first_spring_project.dto.CreateAdvertisementRequest;
import com.example.first_spring_project.dto.AdvertisementDto;
import com.example.first_spring_project.dto.PhotoResponse;
import com.example.first_spring_project.entity.AdvertisementEntity;
import com.example.first_spring_project.entity.CategoryEntity;
import com.example.first_spring_project.entity.PhotoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdvertisementMapper {

    default List<String> mapCategoriesToNames(List<CategoryEntity> categories) {
        if (categories == null) return null;
        return categories.stream()
                .map(CategoryEntity::getName)
                .toList();
    }

    default List<CategoryEntity> mapNamesToCategories(List<String> names) {
        if (names == null) return null;
        return names.stream()
                .map(name -> CategoryEntity.builder().name(name).build())
                .toList();
    }

    @Mapping(source = "categories", target = "categories")
    AdvertisementDto toDto(AdvertisementEntity advertisementEntity);

    @Mapping(source = "categories", target = "categories")
    AdvertisementEntity fromCreateDto(CreateAdvertisementRequest createAdvertisementRequest);

    List<PhotoResponse> map(List<PhotoEntity> photos);
}
