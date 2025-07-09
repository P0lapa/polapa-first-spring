package com.example.first_spring_project.Controllers;

import com.example.first_spring_project.DTOs.AdvertisementCreateDto;
import com.example.first_spring_project.DTOs.AdvertisementDto;
import com.example.first_spring_project.Entities.AdvertisementEntity;
import com.example.first_spring_project.Services.AdvertisementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping
    public ResponseEntity<List<AdvertisementDto>> getAllAdvertisements() {
        return ResponseEntity.ok(advertisementService.getAllAdvertisements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisementById(@PathVariable UUID id) {
        return ResponseEntity.ok(advertisementService.getAdvertisementById(id));
    }

    @PostMapping
    public ResponseEntity<AdvertisementDto> createAdvertisement(@RequestBody AdvertisementCreateDto advertisementCreateDto) {
        return ResponseEntity.ok(advertisementService.createAdvertisement(advertisementCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdvertisementDto> updateAdvertisement(@PathVariable UUID id, @RequestBody AdvertisementCreateDto advertisementUpdateDto) {
        return ResponseEntity.ok(advertisementService.updateAdvertisement(id, advertisementUpdateDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvertisement(@PathVariable UUID id){
        advertisementService.deleteAdvertisement(id);
        return ResponseEntity.noContent().build();
    }

}
