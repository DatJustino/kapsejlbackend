package com.example.kapsejlbackend.serviceTest;

import com.example.kapsejlbackend.model.Deltagere;
import com.example.kapsejlbackend.repository.DeltagereRepository;
import com.example.kapsejlbackend.service.DeltagereService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DeltagereServiceTest {

  @Mock
  private DeltagereRepository deltagereRepository;

  private DeltagereService deltagereService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    deltagereService = new DeltagereService(deltagereRepository);
  }

  @Test
  public void testGetDeltagereById() {
    Long deltagereId = 1L;
    Deltagere deltagere = new Deltagere("John Doe", null, null);

    when(deltagereRepository.findById(deltagereId)).thenReturn(Optional.of(deltagere));

    Deltagere result = deltagereService.getDeltagereById(deltagereId);

    assertEquals(deltagere, result);
    verify(deltagereRepository, times(1)).findById(deltagereId);
  }

  @Test
  public void testCreateDeltagere() {
    Deltagere deltagere = new Deltagere("John Doe", null, null);

    when(deltagereRepository.save(deltagere)).thenReturn(deltagere);

    Deltagere result = deltagereService.createDeltagere(deltagere);

    assertEquals(deltagere, result);
    verify(deltagereRepository, times(1)).save(deltagere);
  }

  @Test
  public void testUpdateDeltagere() {
    Long deltagereId = 1L;
    Deltagere existingDeltagere = new Deltagere("John Doe", null, null);
    Deltagere updatedDeltagere = new Deltagere("Jane Smith", null, null);

    when(deltagereRepository.findById(deltagereId)).thenReturn(Optional.of(existingDeltagere));
    when(deltagereRepository.save(existingDeltagere)).thenReturn(updatedDeltagere);

    Deltagere result = deltagereService.updateDeltagere(deltagereId, updatedDeltagere);

    assertEquals(updatedDeltagere, result);
    verify(deltagereRepository, times(1)).findById(deltagereId);
    verify(deltagereRepository, times(1)).save(existingDeltagere);
  }

  @Test
  public void testDeleteDeltagere() {
    Long deltagereId = 1L;

    deltagereService.deleteDeltagere(deltagereId);

    verify(deltagereRepository, times(1)).deleteById(deltagereId);
  }
}
