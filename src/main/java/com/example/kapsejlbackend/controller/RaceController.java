package com.example.kapsejlbackend.controller;

import com.example.kapsejlbackend.model.Race;
import com.example.kapsejlbackend.model.Sailboat;
import com.example.kapsejlbackend.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={"http://localhost:8080", "http://localhost:63342"})
@RequestMapping("/kapsejladser")
public class RaceController {

  private final RaceService raceService;

  @Autowired
  public RaceController(RaceService raceService) {
    this.raceService = raceService;
  }

  @GetMapping
  public ResponseEntity<List<Race>> getAllRaces() {
    List<Race> raceList = raceService.getAllRaces();
    return ResponseEntity.ok(raceList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Race> getRaceById(@PathVariable Long id) {
    Race race = raceService.getRaceById(id);
    return ResponseEntity.ok(race);
  }
  @GetMapping("/{id}/sejlbaade")
  public ResponseEntity<List<Sailboat>> getParticipantsStandings(@PathVariable Long id) {
    // Get the deltagere' standings for the race with the specified id
    List<Sailboat> sailBoats = raceService.getParticipantsStandings(id);
    return ResponseEntity.ok(sailBoats);
  }

  @PostMapping
  public ResponseEntity<Race> createRaces(@RequestBody Race race) {
    Race createdRace = raceService.createRace(race);
    return ResponseEntity.ok(createdRace);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Race> updateRaces(@PathVariable Long id, @RequestBody Race race) {
    Race updatedRace = raceService.updateRaces(id, race);
    return ResponseEntity.ok(updatedRace);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRaces(@PathVariable Long id) {
    raceService.deleteRace(id);
    return ResponseEntity.noContent().build();
  }
}

