package com.example.kapsejlbackend.controllerTest;

import com.example.kapsejlbackend.controller.KapsejladserController;
import com.example.kapsejlbackend.model.*;
import com.example.kapsejlbackend.service.KapsejladserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class KapsejladserControllerTest {

  @Mock
  private KapsejladserService kapsejladserService;

  private KapsejladserController kapsejladserController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    kapsejladserController = new KapsejladserController(kapsejladserService);
  }

  @Test
  public void testGetAllKapsejladser() {
    // Create a list of Kapsejladser for testing
    List<Kapsejladser> kapsejladserList = Arrays.asList(
        new Kapsejladser(LocalDate.now().minusDays(1)),
        new Kapsejladser(LocalDate.now().minusDays(2)));

    when(kapsejladserService.getAllKapsejladser()).thenReturn(kapsejladserList);

    ResponseEntity<List<Kapsejladser>> response = kapsejladserController.getAllKapsejladser();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(kapsejladserList, response.getBody());
    verify(kapsejladserService, times(1)).getAllKapsejladser();
  }

  @Test
  public void testGetKapsejladserById() {
    Long kapsejladserId = 1L;
    Kapsejladser kapsejladser = new Kapsejladser(LocalDate.now().minusDays(1));


    when(kapsejladserService.getKapsejladserById(kapsejladserId)).thenReturn(kapsejladser);

    ResponseEntity<Kapsejladser> response = kapsejladserController.getKapsejladserById(kapsejladserId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(kapsejladser, response.getBody());
    verify(kapsejladserService, times(1)).getKapsejladserById(kapsejladserId);
  }

  @Test
  public void testGetRaceTimesForKapsejlads() {
    Long kapsejladserId = 1L;

    Kapsejladser kapsejladser = mock(Kapsejladser.class);
    Sejlbaade sailboat = mock(Sejlbaade.class);
    Kapsejladser kapsejladser2 = mock(Kapsejladser.class);
    Sejlbaade sailboat2 = mock(Sejlbaade.class);

    List<RaceTime> raceTimes = Arrays.asList(
        new RaceTime(LocalTime.now(), sailboat, kapsejladser),
        new RaceTime(LocalTime.now(), sailboat2, kapsejladser2)
    );

    when(kapsejladserService.getKapsejladserById(kapsejladserId)).thenReturn(kapsejladser);
    when(kapsejladser.getRaceTimes()).thenReturn(raceTimes);

    ResponseEntity<List<RaceTime>> response = kapsejladserController.getRaceTimesForKapsejlads(kapsejladserId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(raceTimes, response.getBody());
    verify(kapsejladserService, times(1)).getKapsejladserById(kapsejladserId);
  }

  @Test
  public void testAddRaceTimeToKapsejlads() {
    Long kapsejladserId = 1L;
    Sejlbaade Sailboat2 = new Sejlbaade("Sailboat", BaadType.MINDRE_END_25_FOD);
    Kapsejladser kapsejladser2 = new Kapsejladser(LocalDate.now().minusDays(1));
    RaceTime raceTime = new RaceTime(LocalTime.now(), Sailboat2, kapsejladser2);
    when(kapsejladserService.getKapsejladserById(kapsejladserId)).thenReturn(kapsejladser2);
    when(kapsejladserService.updateKapsejladser(kapsejladserId, kapsejladser2)).thenReturn(kapsejladser2);

    ResponseEntity<Kapsejladser> response = kapsejladserController.addRaceTimeToKapsejlads(kapsejladserId, raceTime);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(kapsejladser2, response.getBody());
    verify(kapsejladserService, times(1)).getKapsejladserById(kapsejladserId);
    verify(kapsejladserService, times(1)).updateKapsejladser(kapsejladserId, kapsejladser2);
  }

  @Test
  public void testCreateKapsejladser() {
    Kapsejladser kapsejladser = new Kapsejladser(LocalDate.now().minusDays(1));

    when(kapsejladserService.createKapsejladser(kapsejladser)).thenReturn(kapsejladser);

    ResponseEntity<Kapsejladser> response = kapsejladserController.createKapsejladser(kapsejladser);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(kapsejladser, response.getBody());
    verify(kapsejladserService, times(1)).createKapsejladser(kapsejladser);
  }

  @Test
  public void testUpdateKapsejladser() {
    Long kapsejladserId = 1L;
    Kapsejladser kapsejladser = new Kapsejladser(LocalDate.now().minusDays(1));

    when(kapsejladserService.updateKapsejladser(kapsejladserId, kapsejladser)).thenReturn(kapsejladser);

    ResponseEntity<Kapsejladser> response = kapsejladserController.updateKapsejladser(kapsejladserId, kapsejladser);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(kapsejladser, response.getBody());
    verify(kapsejladserService, times(1)).updateKapsejladser(kapsejladserId, kapsejladser);
  }

  @Test
  public void testDeleteKapsejladser() {
    Long kapsejladserId = 1L;

    ResponseEntity<Void> response = kapsejladserController.deleteKapsejladser(kapsejladserId);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(kapsejladserService, times(1)).deleteKapsejladser(kapsejladserId);
  }
}