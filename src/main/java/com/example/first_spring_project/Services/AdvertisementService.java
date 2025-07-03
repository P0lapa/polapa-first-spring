package com.example.first_spring_project.Services;

import com.example.first_spring_project.Entities.AdvertisementEntity;
import com.example.first_spring_project.Repositories.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public List<AdvertisementEntity> getAllAdvertisements() {
        return advertisementRepository.findAll();
    }

    public Optional<AdvertisementEntity> getAdvertisementById(UUID id) {
        return advertisementRepository.findById(id);
    }

    public void createAdvertisement(AdvertisementEntity advertisementEntity) {
        advertisementRepository.save(advertisementEntity);
    }

     public void updateAdvertisement(UUID id, AdvertisementEntity updatedAdvertisementEntity) {
         advertisementRepository.findById(id)
                 .map(existing -> {
                     existing.setTitle(updatedAdvertisementEntity.getTitle());
                     existing.setDescription(updatedAdvertisementEntity.getDescription());
                     existing.setPhone(updatedAdvertisementEntity.getPhone());
                     existing.setCategory(updatedAdvertisementEntity.getCategory());
                     return advertisementRepository.save(existing);
                 })
                 .orElseThrow(() -> new RuntimeException("Advertisement not found"));
     }

     public void deleteAdvertisement(UUID id) {
        advertisementRepository.deleteById(id);
     }
}
