package com.example.kapsejlbackend.controller;

import com.example.kapsejlbackend.model.Sejlbaade;
import com.example.kapsejlbackend.service.SejlbaadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={"http://localhost:8080", "http://localhost:63342"})
@RequestMapping("/sejlbaade")
public class SejlbaadeController {

  private final SejlbaadeService sejlbaadeService;

  @Autowired
  public SejlbaadeController(SejlbaadeService sejlbaadeService) {
    this.sejlbaadeService = sejlbaadeService;
  }

  @GetMapping
  public ResponseEntity<List<Sejlbaade>> getAllSejlbaade() {
    List<Sejlbaade> sejlbaadeList = sejlbaadeService.getAllSejlbaade();
    return ResponseEntity.ok(sejlbaadeList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Sejlbaade> getSejlbaadeById(@PathVariable Long id) {
    Sejlbaade sejlbaade = sejlbaadeService.getSejlbaadeById(id);
    return ResponseEntity.ok(sejlbaade);
  }

  @PostMapping
  public ResponseEntity<Sejlbaade> createSejlbaade(@RequestBody Sejlbaade sejlbaade) {
    Sejlbaade createdSejlbaade = sejlbaadeService.createSejlbaade(sejlbaade);
    return ResponseEntity.ok(createdSejlbaade);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Sejlbaade> updateSejlbaade(@PathVariable Long id, @RequestBody Sejlbaade sejlbaade) {
    Sejlbaade updatedSejlbaade = sejlbaadeService.updateSejlbaade(id, sejlbaade);
    return ResponseEntity.ok(updatedSejlbaade);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSejlbaade(@PathVariable Long id) {
    sejlbaadeService.deleteSejlbaade(id);
    return ResponseEntity.noContent().build();
  }
}
