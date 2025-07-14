package com.example.first_spring_project.repository;

import com.example.first_spring_project.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoRepository extends JpaRepository<PhotoEntity, UUID> {
}
