package com.example.kapsejlbackend.controller;

import com.example.kapsejlbackend.model.Participant;
import com.example.kapsejlbackend.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={"http://localhost:8080", "http://localhost:63342"})
@RequestMapping("/deltagere")
public class ParticipantController {

  private final ParticipantService participantService;

  @Autowired
  public ParticipantController(ParticipantService participantService) {
    this.participantService = participantService;
  }

  @GetMapping
  public ResponseEntity<List<Participant>> getAllDeltagere() {
    List<Participant> participantList = participantService.getAllParticipants();
    return ResponseEntity.ok(participantList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Participant> getParticipantsById(@PathVariable Long id) {
    Participant participant = participantService.getParticipantsById(id);
    return ResponseEntity.ok(participant);
  }

  @PostMapping
  public ResponseEntity<Participant> createParticipants(@RequestBody Participant participant) {
    Participant createdParticipant = participantService.createParticipant(participant);
    return ResponseEntity.ok(createdParticipant);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Participant> updateParticipants(@PathVariable Long id, @RequestBody Participant participant) {
    Participant updatedParticipant = participantService.updateParticipants(id, participant);
    return ResponseEntity.ok(updatedParticipant);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteParticipants(@PathVariable Long id) {
    participantService.deleteParticipant(id);
    return ResponseEntity.noContent().build();
  }
}

