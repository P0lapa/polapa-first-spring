package com.example.first_spring_project.repository;

import com.example.first_spring_project.entity.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, UUID> {}
