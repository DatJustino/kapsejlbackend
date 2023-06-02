package com.example.kapsejlbackend.controllerTest;

import com.example.kapsejlbackend.controller.DeltagereController;
import com.example.kapsejlbackend.model.Deltagere;
import com.example.kapsejlbackend.service.DeltagereService;
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

public class DeltagereControllerTest {

  @Mock
  private DeltagereService deltagereService;

  private DeltagereController deltagereController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    deltagereController = new DeltagereController(deltagereService);
  }

  @Test
  public void testGetAllDeltagere() {
    // Create a list of Deltagere for testing
    List<Deltagere> deltagereList = Arrays.asList(
        new Deltagere("John Doe", null, null),
        new Deltagere("Jane Smith", null, null)
    );

    when(deltagereService.getAllDeltagere()).thenReturn(deltagereList);

    ResponseEntity<List<Deltagere>> response = deltagereController.getAllDeltagere();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(deltagereList, response.getBody());
    verify(deltagereService, times(1)).getAllDeltagere();
  }

  @Test
  public void testGetDeltagereById() {
    Long deltagereId = 1L;
    Deltagere deltagere = new Deltagere("John Doe", null, null);

    when(deltagereService.getDeltagereById(deltagereId)).thenReturn(deltagere);

    ResponseEntity<Deltagere> response = deltagereController.getDeltagereById(deltagereId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(deltagere, response.getBody());
    verify(deltagereService, times(1)).getDeltagereById(deltagereId);
  }

  @Test
  public void testCreateDeltagere() {
    Deltagere deltagere = new Deltagere("John Doe", null, null);

    when(deltagereService.createDeltagere(deltagere)).thenReturn(deltagere);

    ResponseEntity<Deltagere> response = deltagereController.createDeltagere(deltagere);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(deltagere, response.getBody());
    verify(deltagereService, times(1)).createDeltagere(deltagere);
  }

  @Test
  public void testUpdateDeltagere() {
    Long deltagereId = 1L;
    Deltagere deltagere = new Deltagere("John Doe", null, null);

    when(deltagereService.updateDeltagere(deltagereId, deltagere)).thenReturn(deltagere);

    ResponseEntity<Deltagere> response = deltagereController.updateDeltagere(deltagereId, deltagere);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(deltagere, response.getBody());
    verify(deltagereService, times(1)).updateDeltagere(deltagereId, deltagere);
  }

  @Test
  public void testDeleteDeltagere() {
    Long deltagereId = 1L;

    ResponseEntity<Void> response = deltagereController.deleteDeltagere(deltagereId);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(deltagereService, times(1)).deleteDeltagere(deltagereId);
  }
}
