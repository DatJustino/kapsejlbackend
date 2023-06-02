package com.example.kapsejlbackend.modelTest;

import com.example.kapsejlbackend.model.Deltagere;
import com.example.kapsejlbackend.model.Kapsejladser;
import com.example.kapsejlbackend.model.Sejlbaade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeltagereTest {

  private Deltagere deltagere;
  private Sejlbaade sejlbaade;
  private Kapsejladser kapsejladser;

  @BeforeEach
  public void setUp() {
    sejlbaade = new Sejlbaade(); // Create a Sejlbaade object for testing
    kapsejladser = new Kapsejladser(); // Create a Kapsejladser object for testing

    deltagere = new Deltagere("John Doe", sejlbaade, kapsejladser);
  }

  @Test
  public void testGetKaptajnsNavn() {
    assertEquals("John Doe", deltagere.getKaptajnsNavn());
  }

  @Test
  public void testGetSejlbaade() {
    assertEquals(sejlbaade, deltagere.getSejlbaade());
  }

  @Test
  public void testGetKapsejladser() {
    assertEquals(kapsejladser, deltagere.getKapsejladser());
  }

  @Test
  public void testGetPoint() {
    // Test the initial value of point
    assertEquals(0, deltagere.getPoint());

    // Test setting a new value of point
    deltagere.setPoint(10);
    assertEquals(10, deltagere.getPoint());
  }
}
