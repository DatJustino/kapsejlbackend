package com.example.kapsejlbackend.serviceTest;

import com.example.kapsejlbackend.model.BaadType;
import com.example.kapsejlbackend.model.Sejlbaade;
import com.example.kapsejlbackend.repository.SejlbaadeRepository;
import com.example.kapsejlbackend.service.SejlbaadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SejlbaadeServiceTest {

  @Mock
  private SejlbaadeRepository sejlbaadeRepository;

  private SejlbaadeService sejlbaadeService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    sejlbaadeService = new SejlbaadeService(sejlbaadeRepository);
  }

  @Test
  public void testGetAllSejlbaade() {
    List<Sejlbaade> sejlbaadeList = Arrays.asList(
        new Sejlbaade("Sailboat 1", BaadType.MINDRE_END_25_FOD),
        new Sejlbaade("Sailboat 2", BaadType.MINDRE_END_25_FOD)
    );

    when(sejlbaadeRepository.findAll()).thenReturn(sejlbaadeList);

    List<Sejlbaade> result = sejlbaadeService.getAllSejlbaade();

    assertEquals(sejlbaadeList.size(), result.size());
    assertEquals(sejlbaadeList.get(0).getBaadNavn(), result.get(0).getBaadNavn());
    assertEquals(sejlbaadeList.get(1).getBaadNavn(), result.get(1).getBaadNavn());
    verify(sejlbaadeRepository, times(1)).findAll();
  }

  @Test
  public void testGetSejlbaadeById() {
    Long sejlbaadeId = 1L;
    Sejlbaade sejlbaade = new Sejlbaade("Sailboat", BaadType.MINDRE_END_25_FOD);

    when(sejlbaadeRepository.findById(sejlbaadeId)).thenReturn(Optional.of(sejlbaade));

    Sejlbaade result = sejlbaadeService.getSejlbaadeById(sejlbaadeId);

    assertEquals(sejlbaade, result);
    verify(sejlbaadeRepository, times(1)).findById(sejlbaadeId);
  }

  @Test
  public void testCreateSejlbaade() {
    Sejlbaade sejlbaade = new Sejlbaade("Sailboat", BaadType.MINDRE_END_25_FOD);

    when(sejlbaadeRepository.save(sejlbaade)).thenReturn(sejlbaade);

    Sejlbaade result = sejlbaadeService.createSejlbaade(sejlbaade);

    assertEquals(sejlbaade, result);
    verify(sejlbaadeRepository, times(1)).save(sejlbaade);
  }

  @Test
  public void testUpdateSejlbaade() {
    Long sejlbaadeId = 1L;
    Sejlbaade existingSejlbaade = new Sejlbaade("Sailboat", BaadType.MINDRE_END_25_FOD);
    Sejlbaade updatedSejlbaade = new Sejlbaade("Updated Sailboat", BaadType.STOERRE_END_40_FOD);

    when(sejlbaadeRepository.findById(sejlbaadeId)).thenReturn(Optional.of(existingSejlbaade));
    when(sejlbaadeRepository.save(existingSejlbaade)).thenReturn(updatedSejlbaade);

    Sejlbaade result = sejlbaadeService.updateSejlbaade(sejlbaadeId, updatedSejlbaade);

    assertEquals(updatedSejlbaade, result);
    verify(sejlbaadeRepository, times(1)).findById(sejlbaadeId);
    verify(sejlbaadeRepository, times(1)).save(existingSejlbaade);
  }

  @Test
  public void testDeleteSejlbaade() {
    Long sejlbaadeId = 1L;

    sejlbaadeService.deleteSejlbaade(sejlbaadeId);

    verify(sejlbaadeRepository, times(1)).deleteById(sejlbaadeId);
  }
}
