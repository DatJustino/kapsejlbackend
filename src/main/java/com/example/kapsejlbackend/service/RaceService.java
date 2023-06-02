package com.example.kapsejlbackend.service;

import com.example.kapsejlbackend.model.Participant;
import com.example.kapsejlbackend.model.Race;
import com.example.kapsejlbackend.model.Sailboat;
import com.example.kapsejlbackend.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RaceService {

  private final RaceRepository raceRepository;

  @Autowired
  public RaceService(RaceRepository raceRepository) {
    this.raceRepository = raceRepository;
  }

  public List<Race> getAllRaces() {
    return raceRepository.findAll();
  }

  public Race getRaceById(Long id) {
    Optional<Race> race = raceRepository.findById(id);
    return race.orElse(null);
  }

  public Race createRace(Race race) {
    return raceRepository.save(race);
  }

  public Race updateRaces(Long id, Race race) {
    Optional<Race> existingRace = raceRepository.findById(id);
    if (existingRace.isPresent()) {
      Race updatedRace = existingRace.get();
      updatedRace.setDate(race.getDate());
      return raceRepository.save(updatedRace);
    }
    return null;
  }

  public void deleteRace(Long id) {
    raceRepository.deleteById(id);
  }

  public List<Sailboat> getParticipantsStandings(Long raceId) {
    Race race = raceRepository.findById(raceId).orElse(null);
    if (race == null) {
      throw new IllegalArgumentException("Race not found with ID: " + raceId);
    }

    // Retrieve the boats participating in the race
    List<Sailboat> boats = race.getParticipants().stream()
        .map(Participant::getSailBoat)
        .collect(Collectors.toList());

    // Sort the boats by their points
    boats.sort(Comparator.comparing(Sailboat::getPoint));

    // Set the standings for each boat
    for (int i = 0; i < boats.size(); i++) {
      Sailboat boat = boats.get(i);
      boat.setStanding(i + 1);
    }
    return boats;
  }
/*  public List<Race> getRacesOrderByDateAndTime() {
    return raceRepository.findAllByOrderByDateAscTimeAsc();
  }*/

}

