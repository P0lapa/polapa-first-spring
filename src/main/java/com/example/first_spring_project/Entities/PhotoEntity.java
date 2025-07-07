package com.example.first_spring_project.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "photos")
@Builder
public class PhotoEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "advertisement_id")
    private AdvertisementEntity advertisementId;
}
