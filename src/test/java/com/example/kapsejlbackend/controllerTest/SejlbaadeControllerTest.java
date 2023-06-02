package com.example.kapsejlbackend.controllerTest;

import com.example.kapsejlbackend.controller.SejlbaadeController;
import com.example.kapsejlbackend.model.BaadType;
import com.example.kapsejlbackend.model.Sejlbaade;
import com.example.kapsejlbackend.service.SejlbaadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SejlbaadeControllerTest {

  @Mock
  private SejlbaadeService sejlbaadeService;

  private SejlbaadeController sejlbaadeController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    sejlbaadeController = new SejlbaadeController(sejlbaadeService);
  }

  @Test
  public void testGetAllSejlbaade() {
    // Create a list of Sejlbaade for testing
    List<Sejlbaade> sejlbaadeList = Arrays.asList(
        new Sejlbaade("Sailboat 1", BaadType.MINDRE_END_25_FOD),
        new Sejlbaade("Sailboat 2", BaadType.STOERRE_END_40_FOD)
    );

    when(sejlbaadeService.getAllSejlbaade()).thenReturn(sejlbaadeList);

    ResponseEntity<List<Sejlbaade>> response = sejlbaadeController.getAllSejlbaade();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(sejlbaadeList, response.getBody());
    verify(sejlbaadeService, times(1)).getAllSejlbaade();
  }

  @Test
  public void testGetSejlbaadeById() {
    Long sejlbaadeId = 1L;
    Sejlbaade sejlbaade = new Sejlbaade("Sailboat 2", BaadType.STOERRE_END_40_FOD);

    when(sejlbaadeService.getSejlbaadeById(sejlbaadeId)).thenReturn(sejlbaade);

    ResponseEntity<Sejlbaade> response = sejlbaadeController.getSejlbaadeById(sejlbaadeId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(sejlbaade, response.getBody());
    verify(sejlbaadeService, times(1)).getSejlbaadeById(sejlbaadeId);
  }

  @Test
  public void testCreateSejlbaade() {
    Sejlbaade sejlbaade = new Sejlbaade("Sailboat 1", BaadType.MELLEM_25_OG_40_FOD);

    when(sejlbaadeService.createSejlbaade(sejlbaade)).thenReturn(sejlbaade);

    ResponseEntity<Sejlbaade> response = sejlbaadeController.createSejlbaade(sejlbaade);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(sejlbaade, response.getBody());
    verify(sejlbaadeService, times(1)).createSejlbaade(sejlbaade);
  }

  @Test
  public void testUpdateSejlbaade() {
    Long sejlbaadeId = 1L;
    Sejlbaade sejlbaade = new Sejlbaade("Sailboat 1", BaadType.MELLEM_25_OG_40_FOD);

    when(sejlbaadeService.updateSejlbaade(sejlbaadeId, sejlbaade)).thenReturn(sejlbaade);

    ResponseEntity<Sejlbaade> response = sejlbaadeController.updateSejlbaade(sejlbaadeId, sejlbaade);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(sejlbaade, response.getBody());
    verify(sejlbaadeService, times(1)).updateSejlbaade(sejlbaadeId, sejlbaade);
  }

  @Test
  public void testDeleteSejlbaade() {
    Long sejlbaadeId = 1L;

    ResponseEntity<Void> response = sejlbaadeController.deleteSejlbaade(sejlbaadeId);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(sejlbaadeService, times(1)).deleteSejlbaade(sejlbaadeId);
  }
}
