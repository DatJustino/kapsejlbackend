package com.example.kapsejlbackend.controller;

import com.example.kapsejlbackend.model.Deltagere;
import com.example.kapsejlbackend.service.DeltagereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={"http://localhost:8080", "http://localhost:63342"})
@RequestMapping("/deltagere")
public class DeltagereController {

  private final DeltagereService deltagereService;

  @Autowired
  public DeltagereController(DeltagereService deltagereService) {
    this.deltagereService = deltagereService;
  }

  @GetMapping
  public ResponseEntity<List<Deltagere>> getAllDeltagere() {
    List<Deltagere> deltagereList = deltagereService.getAllDeltagere();
    return ResponseEntity.ok(deltagereList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Deltagere> getDeltagereById(@PathVariable Long id) {
    Deltagere deltagere = deltagereService.getDeltagereById(id);
    return ResponseEntity.ok(deltagere);
  }

  @PostMapping
  public ResponseEntity<Deltagere> createDeltagere(@RequestBody Deltagere deltagere) {
    Deltagere createdDeltagere = deltagereService.createDeltagere(deltagere);
    return ResponseEntity.ok(createdDeltagere);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Deltagere> updateDeltagere(@PathVariable Long id, @RequestBody Deltagere deltagere) {
    Deltagere updatedDeltagere = deltagereService.updateDeltagere(id, deltagere);
    return ResponseEntity.ok(updatedDeltagere);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDeltagere(@PathVariable Long id) {
    deltagereService.deleteDeltagere(id);
    return ResponseEntity.noContent().build();
  }
}

