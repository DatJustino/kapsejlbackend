package com.example.kapsejlbackend.controller;

import com.example.kapsejlbackend.model.Sailboat;
import com.example.kapsejlbackend.service.SailboatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={"http://localhost:8080", "http://localhost:63342"})
@RequestMapping("/sejlbaade")
public class SailboatController {

  private final SailboatService sailboatService;

  @Autowired
  public SailboatController(SailboatService sailboatService) {
    this.sailboatService = sailboatService;
  }

  @GetMapping
  public ResponseEntity<List<Sailboat>> getAllSailboats() {
    List<Sailboat> sailboatList = sailboatService.getAllSailboats();
    return ResponseEntity.ok(sailboatList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Sailboat> getSailboatById(@PathVariable Long id) {
    Sailboat sailBoats = sailboatService.getSailboatById(id);
    return ResponseEntity.ok(sailBoats);
  }

  @PostMapping
  public ResponseEntity<Sailboat> createSailboat(@RequestBody Sailboat sailboat) {
    Sailboat createdSailboat = sailboatService.createSailboat(sailboat);
    return ResponseEntity.ok(createdSailboat);
  }
  @PostMapping("/{sailboatId}/kapsejlads/{raceId}")
  public ResponseEntity<?> setRaceforSailboats(@PathVariable Long sailboatId, @PathVariable Long raceId) {
    try {
      sailboatService.setRaceforSailboats(sailboatId, raceId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
  @PutMapping("/{id}")
  public ResponseEntity<Sailboat> updateSailboat(@PathVariable Long id, @RequestBody Sailboat sailBoats) {
    Sailboat updatedSailboat = sailboatService.updateSailboat(id, sailBoats);
    return ResponseEntity.ok(updatedSailboat);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSailboat(@PathVariable Long id) {
    sailboatService.deleteSailboat(id);
    return ResponseEntity.noContent().build();
  }
}
