package com.example.kapsejlbackend.service;

import com.example.kapsejlbackend.model.RaceResults;
import com.example.kapsejlbackend.repository.RaceResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RaceResultsService {

  private final RaceResultsRepository raceResultsRepository;

  @Autowired
  public RaceResultsService(RaceResultsRepository raceResultsRepository) {
    this.raceResultsRepository = raceResultsRepository;
  }

  public RaceResults createRaceResults(RaceResults raceResults) {
    return raceResultsRepository.save(raceResults);
  }

  public List<RaceResults> getAllRaceResults() {
    return raceResultsRepository.findAll();
  }

  public Optional<RaceResults> getRaceResultsById(Long id) {
    return raceResultsRepository.findById(id);
  }

  public RaceResults updateSailboatResult(Long id, RaceResults updateRaceResult) {
    Optional<RaceResults> existingRaceResult = raceResultsRepository.findById(id);
    if (existingRaceResult.isPresent()) {
      RaceResults raceResult = existingRaceResult.get();
      raceResult.setBoatId(updateRaceResult.getBoatId());
      raceResult.setElapsedTime(updateRaceResult.getElapsedTime());
      raceResult.setRace(updateRaceResult.getRace());
      raceResult.setSailboat(updateRaceResult.getSailboat());
      return raceResultsRepository.save(raceResult);
    }
    return null;
  }

  public void deleteRaceResult(Long id) {
    raceResultsRepository.deleteById(id);
  }

}
