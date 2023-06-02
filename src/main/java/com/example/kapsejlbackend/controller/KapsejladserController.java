package com.example.kapsejlbackend.controller;

import com.example.kapsejlbackend.model.Kapsejladser;
import com.example.kapsejlbackend.model.RaceTime;
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
  @GetMapping("/{id}/sejlbaade")
  public ResponseEntity<List<RaceTime>> getRaceTimesForKapsejlads(@PathVariable Long id) {
    Kapsejladser kapsejladser = kapsejladserService.getKapsejladserById(id);
    if (kapsejladser != null) {
      List<RaceTime> raceTimes = kapsejladser.getRaceTimes();
      return ResponseEntity.ok(raceTimes);
    } else {
      return ResponseEntity.notFound().build();
    }
  }


  @PostMapping("/{id}/sejlbaade")
  public ResponseEntity<Kapsejladser> addRaceTimeToKapsejlads(@PathVariable Long id, @RequestBody RaceTime raceTime) {
    Kapsejladser kapsejladser = kapsejladserService.getKapsejladserById(id);
    if (kapsejladser != null) {
      kapsejladser.addRaceTime(raceTime);
      kapsejladserService.updateKapsejladser(id, kapsejladser);
      return ResponseEntity.ok(kapsejladser);
    } else {
      return ResponseEntity.notFound().build();
    }
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

