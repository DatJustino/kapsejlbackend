package com.example.kapsejlbackend.controller;

import com.example.kapsejlbackend.model.Kapsejladser;
import com.example.kapsejlbackend.service.KapsejladserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={"http://localhost:8080", "http://localhost:63342"})
@RequestMapping("/kapsejladser")
public class KapsejladserController {

  private final KapsejladserService kapsejladserService;

  @Autowired
  public KapsejladserController(KapsejladserService kapsejladserService) {
    this.kapsejladserService = kapsejladserService;
  }

  @GetMapping
  public ResponseEntity<List<Kapsejladser>> getAllKapsejladser() {
    List<Kapsejladser> kapsejladserList = kapsejladserService.getAllKapsejladser();
    return ResponseEntity.ok(kapsejladserList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Kapsejladser> getKapsejladserById(@PathVariable Long id) {
    Kapsejladser kapsejladser = kapsejladserService.getKapsejladserById(id);
    return ResponseEntity.ok(kapsejladser);
  }

  @PostMapping
  public ResponseEntity<Kapsejladser> createKapsejladser(@RequestBody Kapsejladser kapsejladser) {
    Kapsejladser createdKapsejladser = kapsejladserService.createKapsejladser(kapsejladser);
    return ResponseEntity.ok(createdKapsejladser);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Kapsejladser> updateKapsejladser(@PathVariable Long id, @RequestBody Kapsejladser kapsejladser) {
    Kapsejladser updatedKapsejladser = kapsejladserService.updateKapsejladser(id, kapsejladser);
    return ResponseEntity.ok(updatedKapsejladser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteKapsejladser(@PathVariable Long id) {
    kapsejladserService.deleteKapsejladser(id);
    return ResponseEntity.noContent().build();
  }
}

