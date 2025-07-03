package com.example.first_spring_project.Controllers;

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
    public List<AdvertisementEntity> getAllAdvertisements() {
        return advertisementService.getAllAdvertisements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementEntity> getAdvertisementById(@PathVariable UUID id) {
        Optional<AdvertisementEntity> advertisement = advertisementService.getAdvertisementById(id);
        return advertisement
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void createAdvertisement(@RequestBody AdvertisementEntity advertisementEntity) {
        advertisementService.createAdvertisement(advertisementEntity);
    }

    @PutMapping("/{id}")
    public void updateAdvertisement(@PathVariable UUID id, @RequestBody AdvertisementEntity advertisementEntity) {
        advertisementService.updateAdvertisement(id, advertisementEntity);
    }


    @DeleteMapping("/{id}")
    public void deleteAdvertisement(@PathVariable UUID id){
        advertisementService.deleteAdvertisement(id);
    }

}
