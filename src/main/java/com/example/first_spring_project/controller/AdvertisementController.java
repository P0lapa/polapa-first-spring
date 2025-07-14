package com.example.first_spring_project.controller;

import com.example.first_spring_project.dto.CreateAdvertisementRequest;
import com.example.first_spring_project.dto.AdvertisementDto;
import com.example.first_spring_project.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/advertisement")
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @GetMapping
    public ResponseEntity<List<AdvertisementDto>> getAllAdvertisements() {
        return ResponseEntity.ok(advertisementService.getAllAdvertisements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisementById(@PathVariable UUID id) {
        return ResponseEntity.ok(advertisementService.getAdvertisementById(id));
    }

    @PostMapping
    public ResponseEntity<AdvertisementDto> createAdvertisement(@RequestBody CreateAdvertisementRequest createAdvertisementRequest) {
        return ResponseEntity.ok(advertisementService.createAdvertisement(createAdvertisementRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdvertisementDto> updateAdvertisement(@PathVariable UUID id, @RequestBody CreateAdvertisementRequest advertisementUpdateDto) {
        return ResponseEntity.ok(advertisementService.updateAdvertisement(id, advertisementUpdateDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvertisement(@PathVariable UUID id){
        advertisementService.deleteAdvertisement(id);
        return ResponseEntity.noContent().build();
    }

}
