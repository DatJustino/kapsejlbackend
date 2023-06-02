package com.example.kapsejlbackend.service;

import com.example.kapsejlbackend.model.BoatTypes;
import com.example.kapsejlbackend.model.Race;
import com.example.kapsejlbackend.model.Sailboat;
import com.example.kapsejlbackend.repository.RaceRepository;
import com.example.kapsejlbackend.repository.SailboatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SailboatService {

  private final SailboatRepository sailboatRepository;
  private final RaceRepository raceRepository;

  @Autowired
  public SailboatService(SailboatRepository sailboatRepository, RaceRepository raceRepository) {
    this.sailboatRepository = sailboatRepository;
    this.raceRepository = raceRepository;
  }

  public List<Sailboat> getAllSailboats() {
    List<Sailboat> sailboatList = sailboatRepository.findAll();
    for (Sailboat sailBoats : sailboatList) {
      sailBoats.setBoatDisplayName(sailBoats.getBoatTypes().getDisplayName());
    }
    return sailboatList;
  }


  public Sailboat getSailboatById(Long id) {
    Optional<Sailboat> sailboat = sailboatRepository.findById(id);
    return sailboat.orElse(null);
  }

  public Sailboat createSailboat(Sailboat sailBoats) {
    return sailboatRepository.save(sailBoats);
  }

  public Sailboat updateSailboat(Long id, Sailboat sailboat) {
    Optional<Sailboat> existingSailboat = sailboatRepository.findById(id);
    if (existingSailboat.isPresent()) {
      Sailboat updatedSailboat = existingSailboat.get();
      updatedSailboat.setBoatName(sailboat.getBoatName());
      updatedSailboat.setBoatTypes(sailboat.getBoatTypes());
      updatedSailboat.setPoint(sailboat.getPoint());
      return sailboatRepository.save(updatedSailboat);
    }
    return null;
  }

  public void deleteSailboat(Long id) {
    sailboatRepository.deleteById(id);
  }

  public void setRaceforSailboats(Long sailboatId, Long raceId) {
    Sailboat sailBoats = sailboatRepository.findById(sailboatId)
        .orElseThrow(() -> new IllegalArgumentException("Sailboat not found"));
    Race race = raceRepository.findById(raceId)
        .orElseThrow(() -> new IllegalArgumentException("Race not found"));

    sailBoats.setRace(race);
    sailboatRepository.save(sailBoats);
  }

  public List<Sailboat> getSailboatByType(BoatTypes boatTypes) {
    return sailboatRepository.findByBoatTypes(boatTypes);
  }
  public List<Sailboat> createMultipleSailboats(List<Sailboat> sailboatList) {
    return sailboatRepository.saveAll(sailboatList);
  }

  public void updateMultipleSailboats(List<Sailboat> sailboatList) {
    sailboatRepository.saveAll(sailboatList);
  }
}

