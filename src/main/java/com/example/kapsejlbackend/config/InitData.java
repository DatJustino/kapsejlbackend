package com.example.kapsejlbackend.config;

import com.example.kapsejlbackend.model.*;
import com.example.kapsejlbackend.service.DeltagereService;
import com.example.kapsejlbackend.service.KapsejladserService;
import com.example.kapsejlbackend.service.SejlbaadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

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
    Sejlbaade sejlbaade1 = new Sejlbaade("Boat 1", BaadType.STOERRE_END_40_FOD);
    Sejlbaade sejlbaade2 = new Sejlbaade("Boat 2", BaadType.MINDRE_END_25_FOD);
    Sejlbaade sejlbaade3 = new Sejlbaade("Boat 3", BaadType.MELLEM_25_OG_40_FOD);

    sejlbaade1 = sejlbaadeService.createSejlbaade(sejlbaade1);
    sejlbaade2 = sejlbaadeService.createSejlbaade(sejlbaade2);
    sejlbaade3 = sejlbaadeService.createSejlbaade(sejlbaade3);

    Kapsejladser kapsejladser1 = new Kapsejladser(LocalDate.of(2023, 5, 1));
    Kapsejladser kapsejladser2 = new Kapsejladser(LocalDate.of(2023, 5, 8));
    Kapsejladser kapsejladser3 = new Kapsejladser(LocalDate.of(2023, 5, 15));

    kapsejladser1 = kapsejladserService.createKapsejladser(kapsejladser1);
    kapsejladser2 = kapsejladserService.createKapsejladser(kapsejladser2);
    kapsejladser3 = kapsejladserService.createKapsejladser(kapsejladser3);

    Deltagere deltagere1 = new Deltagere("Harald", sejlbaade1, kapsejladser1);
    Deltagere deltagere2 = new Deltagere("Birk", sejlbaade2, kapsejladser2);
    Deltagere deltagere3 = new Deltagere("Roedskaeg", sejlbaade3, kapsejladser3);

    deltagereService.createDeltagere(deltagere1);
    deltagereService.createDeltagere(deltagere2);
    deltagereService.createDeltagere(deltagere3);

    // Add race times for each participant
    RaceTime raceTime1 = new RaceTime(LocalTime.of(1, 30, 15), sejlbaade1, kapsejladser1);
    RaceTime raceTime2 = new RaceTime(LocalTime.of(1, 25, 45), sejlbaade2, kapsejladser1);
    RaceTime raceTime3 = new RaceTime(LocalTime.of(1, 20, 30), sejlbaade3, kapsejladser1);

    kapsejladser1.addRaceTime(raceTime1);
    kapsejladser1.addRaceTime(raceTime2);
    kapsejladser1.addRaceTime(raceTime3);

    kapsejladserService.updateKapsejladser(kapsejladser1.getId(), kapsejladser1);
  }
}
