package com.example.first_spring_project.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateAdvertisementRequest {

    private String title;
    private String description;
    private String phone;
    private List<String> categories;
    private List<PhotoResponse> photos;
}
