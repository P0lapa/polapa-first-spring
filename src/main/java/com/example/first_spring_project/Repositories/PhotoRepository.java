package com.example.first_spring_project.Repositories;

import com.example.first_spring_project.Entities.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoRepository extends JpaRepository<PhotoEntity, UUID> {
}
