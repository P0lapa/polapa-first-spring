package com.example.first_spring_project.DTOs;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class AdvertisementDto {
    private UUID id;
    private String title;
    private String description;
    private String phone;
    private String category;
    private LocalDateTime createdAt;
    private List<String> photoUrls;
}
