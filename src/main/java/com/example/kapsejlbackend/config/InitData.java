package com.example.kapsejlbackend.config;

import com.example.kapsejlbackend.model.*;
import com.example.kapsejlbackend.repository.ParticipantRepository;
import com.example.kapsejlbackend.repository.RaceRepository;
import com.example.kapsejlbackend.repository.RaceResultsRepository;
import com.example.kapsejlbackend.repository.SailboatRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

  @Configuration
  public class InitData {
    @Bean
    CommandLineRunner initDatabase(SailboatRepository sailboatRepository, ParticipantRepository participantRepository,
                                   RaceRepository raceRepository, RaceResultsRepository raceResultsRepository) {
      return args -> {
        Faker faker = new Faker();
        BoatTypes[] boatTypes = BoatTypes.values();

        for (int i = 0; i < 20; i++) {
          // generate random Sailboat
          Sailboat sailboat = new Sailboat();
          sailboat.setBoatName(faker.name().title());
          sailboat.setBoatTypes(faker.options().option(boatTypes));
          sailboat.setPoint(faker.number().numberBetween(1, 100));
          sailboat = sailboatRepository.save(sailboat);

          // generate random Race
          Race race = new Race(LocalDate.now(), LocalTime.now());
          race = raceRepository.save(race);

          // generate random Participant
          Participant participant = new Participant(faker.name().fullName(), sailboat, race);
          participant = participantRepository.save(participant);

          // generate random RaceResults
          RaceResults raceResults = new RaceResults();
          raceResults.setBoatId(sailboat.getId());
          raceResults.setParticipantId(participant.getId());
          raceResults.setElapsedTime(faker.number().numberBetween(1, 500));
          raceResults.setRace(race);
          raceResults.setSailboat(sailboat);
          raceResultsRepository.save(raceResults);
        }
      };
    }
  }


/*
  @Autowired
  public InitData(SailboatService sailboatService, RaceService raceService, ParticipantService participantService, RaceResultsService raceResultsService) {
    this.sailboatService = sailboatService;
    this.raceService = raceService;
    this.participantService = participantService;
    this.raceResultsService = raceResultsService;
  }
  @Override
  public void run(String... args) throws Exception {
    // Create boats
    List<Sailboat> boats = new ArrayList<>();
    boats.add(new Sailboat(null, "Boat 1", BoatTypes.STOERRE_END_40_FOD, participantService.getParticipantsById(1L), 0, new ArrayList<>(), 0, null));
    boats.add(new Sailboat(null, "Boat 2", BoatTypes.MINDRE_END_25_FOD, participantService.getParticipantsById(2L), 0, new ArrayList<>(), 0, null));
    boats.add(new Sailboat(null, "Boat 3", BoatTypes.MELLEM_25_OG_40_FOD, participantService.getParticipantsById(3L), 0, new ArrayList<>(), 0, null));
    // TODO: Better boat names!
    for (int i = 4; i <= 9; i++) {
      Long id = Long.valueOf(i);
      boats.add(new Sailboat(null, "Boat " + i, getRandomBoatType(), participantService.getParticipantsById(id), 0, new ArrayList<>(), 0, null));
    }
    boats = sailboatService.createMultipleSailboats(boats);

    LocalDate startDate = LocalDate.of(2023, 5, 3); // The first Wednesday
    LocalDate endDate = LocalDate.of(2023, 10, 4); // The last Wednesday

    LocalDate currentWednesday = startDate;

    int maxWeeks = 30;

    while (currentWednesday.isBefore(endDate)) {
      createRaceForBoatType(currentWednesday, LocalTime.of(12, 0), BoatTypes.MINDRE_END_25_FOD); // First race with boats below 25 feet
      createRaceForBoatType(currentWednesday, LocalTime.of(14, 0), BoatTypes.MELLEM_25_OG_40_FOD); // Second race with boats between 25 and 40 feet
      createRaceForBoatType(currentWednesday, LocalTime.of(16, 0), BoatTypes.STOERRE_END_40_FOD); // Last race with boats above 40 feet
      currentWednesday = currentWednesday.plusWeeks(1);

      // Break the loop if the current Wednesday exceeds the maximum number of weeks
      if (currentWednesday.isAfter(startDate.plusWeeks(maxWeeks))) {
        break;
      }
    }
  }

  private void createRaceForBoatType(LocalDate date, LocalTime time, BoatTypes boatType) {
    Race race = new Race(date, time);
    race = raceService.createRace(race);

    List<Sailboat> boats = sailboatService.getSailboatByType(boatType);
    List<Participant> participantList = new ArrayList<>();

    for (Sailboat boat : boats) {
      for (long i = 0; i <= boats.size(); i++) {
        String captainsName = "Participant " + boat.getId() + "-" + i; // Generate a unique captains name
        Participant participant = new Participant(captainsName, boat, race);
        participant = participantService.createParticipant(participant); // Save the participant and assign an ID
        participantList.add(participant);

        int elapsedTime = getRandomElapsedTime(boat.getBoatTypes());
        RaceResults result = new RaceResults(null, participant.getId(), boat.getId(), elapsedTime, race, boat);
        raceResultsService.createRaceResults(result);
        boat.getRaceResults().add(result);
      }
    }

    participantService.createMultipleParticipants(participantList);
    sailboatService.updateMultipleSailboats(boats);
  }
  private Sailboat findAvailableBoat(BoatTypes boatType) {
    List<Sailboat> availableBoats = sailboatService.getSailboatByType(boatType);
    for (Sailboat boat : availableBoats) {
      if (boat.getParticipant() == null) {
        return boat;
      }
    }
    return null; // No available boats
  }

  private BoatTypes getRandomBoatType() {
    BoatTypes[] boatTypes = BoatTypes.values();
    int index = boatCount % boatTypes.length;
    boatCount++;
    return boatTypes[index];
  }
  // Get random times for boats, bigger boats go faster (but not too fast)
  private int getRandomElapsedTime(BoatTypes boatType) {
    Random random = new Random();
    int minTime = 0;
    int maxTime = 0;

    switch (boatType) {
      case STOERRE_END_40_FOD:
        minTime = 20;
        maxTime = 35;
        break;
      case MINDRE_END_25_FOD:
        minTime = 40;
        maxTime = 60;
        break;
      case MELLEM_25_OG_40_FOD:
        minTime = 35;
        maxTime = 50;
        break;
    }
    return random.nextInt(maxTime - minTime + 1) + minTime;
  }
}
*/
