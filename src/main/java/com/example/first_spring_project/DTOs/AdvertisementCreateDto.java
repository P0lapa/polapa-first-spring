package com.example.first_spring_project.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class AdvertisementCreateDto {
    private String title;
    private String description;
    private String phone;
    private String category;
    private List<String> photoUrls;
}
