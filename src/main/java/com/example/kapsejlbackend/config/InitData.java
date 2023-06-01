package com.example.kapsejlbackend.config;

import com.example.kapsejlbackend.model.BaadType;
import com.example.kapsejlbackend.model.Deltagere;
import com.example.kapsejlbackend.model.Kapsejladser;
import com.example.kapsejlbackend.model.Sejlbaade;
import com.example.kapsejlbackend.service.DeltagereService;
import com.example.kapsejlbackend.service.KapsejladserService;
import com.example.kapsejlbackend.service.SejlbaadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class InitData implements CommandLineRunner {

  private final SejlbaadeService sejlbaadeService;
  private final KapsejladserService kapsejladserService;
  private final DeltagereService deltagereService;

  @Autowired
  public InitData(SejlbaadeService sejlbaadeService, KapsejladserService kapsejladserService, DeltagereService deltagereService) {
    this.sejlbaadeService = sejlbaadeService;
    this.kapsejladserService = kapsejladserService;
    this.deltagereService = deltagereService;
  }

  @Override
  public void run(String... args) throws Exception {
    // Create boats
    List<Sejlbaade> boats = new ArrayList<>();
    boats.add(new Sejlbaade(null, "Boat 1", BaadType.STOERRE_END_40_FOD, null, 0, null));
    boats.add(new Sejlbaade(null, "Boat 2", BaadType.MINDRE_END_25_FOD, null, 0, null));
    boats.add(new Sejlbaade(null, "Boat 3", BaadType.MELLEM_25_OG_40_FOD, null, 0, null));

    for (int i = 4; i <= 30; i++) {
      boats.add(new Sejlbaade(null, "Boat " + i, getRandomBoatType(), null, 0, null));
    }

    boats = sejlbaadeService.createMultipleSejlbaade(boats);

    // Create races for each Wednesday
    LocalDate startDate = LocalDate.of(2023, 5, 3); // The first Wednesday in the range
    LocalDate endDate = LocalDate.of(2023, 10, 4); // The last Wednesday in the range

    LocalDate currentWednesday = startDate;
    while (currentWednesday.isBefore(endDate)) {
      createRaceForBoatType(currentWednesday, LocalTime.of(12, 0), BaadType.MINDRE_END_25_FOD); // First race with boats below 25 feet
      createRaceForBoatType(currentWednesday, LocalTime.of(14, 0), BaadType.MELLEM_25_OG_40_FOD); // Second race with boats between 25 and 40 feet
      createRaceForBoatType(currentWednesday, LocalTime.of(16, 0), BaadType.STOERRE_END_40_FOD); // Last race with boats above 40 feet
      currentWednesday = currentWednesday.plusWeeks(1);
    }
  }

  private void createRaceForBoatType(LocalDate date, LocalTime time, BaadType boatType) {
    Kapsejladser race = new Kapsejladser(date, time);
    race = kapsejladserService.createKapsejladser(race);

    List<Sejlbaade> boats = sejlbaadeService.getSejlbaadeByBoatType(boatType);
    List<Deltagere> deltagereList = new ArrayList<>();

    for (Sejlbaade boat : boats) {
      // Check if the boat is already assigned to another participant in a different race
      if (boat.getDeltagere() != null && !boat.getDeltagere().getKapsejladser().getDate().isEqual(date)) {
        // Boat is already assigned to another race, skip this participant
        continue;
      }

      Deltagere deltagere = new Deltagere("Participant " + boat.getId(), boat, race);

      // Assign the boat to the participant
      boat.setDeltagere(deltagere);
      deltagereList.add(deltagere);
    }

    // Save the participants (deltagere) and update the boats
    deltagereService.createMultipleDeltagere(deltagereList);
    sejlbaadeService.updateMultipleSejlbaade(boats);
  }
  private Sejlbaade findAvailableBoat(BaadType boatType) {
    List<Sejlbaade> availableBoats = sejlbaadeService.getSejlbaadeByBoatType(boatType);
    for (Sejlbaade boat : availableBoats) {
      if (boat.getDeltagere() == null) {
        return boat;
      }
    }
    return null; // No available boat of the specified type found
  }

  private BaadType getRandomBoatType() {
    int randomIndex = new Random().nextInt(BaadType.values().length);
    return BaadType.values()[randomIndex];
  }
}
