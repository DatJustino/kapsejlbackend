package com.example.kapsejlbackend.serviceTest;

import com.example.kapsejlbackend.model.BaadType;
import com.example.kapsejlbackend.model.Deltagere;
import com.example.kapsejlbackend.model.RaceTime;
import com.example.kapsejlbackend.model.Sejlbaade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SejlbaadeTest {

  private Sejlbaade sejlbaade;

  @BeforeEach
  public void setUp() {
    sejlbaade = new Sejlbaade("Sailboat", BaadType.MINDRE_END_25_FOD);
  }

  @Test
  public void testConstructorAndGetters() {
    assertEquals("Sailboat", sejlbaade.getBaadNavn());
    assertEquals(BaadType.MINDRE_END_25_FOD, sejlbaade.getBaadType());
  }

  @Test
  public void testSetterAndGetterForBaadTypeDisplayName() {
    sejlbaade.setBaadTypeDisplayName("Small Boat");
    assertEquals("Small Boat", sejlbaade.getBaadTypeDisplayName());
  }

  @Test
  public void testDeltagereAssociation() {
    Deltagere deltagere = new Deltagere("Captain", sejlbaade, null);
    sejlbaade.setDeltagere(deltagere);
    assertEquals(deltagere, sejlbaade.getDeltagere());
  }

  @Test
  public void testRaceTimesAssociation() {
    RaceTime raceTime1 = new RaceTime();
    RaceTime raceTime2 = new RaceTime();
    List<RaceTime> raceTimes = new ArrayList<>();
    raceTimes.add(raceTime1);
    raceTimes.add(raceTime2);

    sejlbaade.setRaceTimes(raceTimes);

    assertEquals(2, sejlbaade.getRaceTimes().size());
    assertEquals(raceTime1, sejlbaade.getRaceTimes().get(0));
    assertEquals(raceTime2, sejlbaade.getRaceTimes().get(1));
  }

  @Test
  public void testTransientField() {
    sejlbaade.setBaadTypeDisplayName("Small Boat");
    assertNotNull(sejlbaade.getBaadTypeDisplayName());
    assertEquals("Small Boat", sejlbaade.getBaadTypeDisplayName());
  }
}
