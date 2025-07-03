package com.example.first_spring_project.Repositories;

import com.example.first_spring_project.Entities.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, UUID> {}
