package com.example.kapsejlbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "kapsejladser")
@Data
@NoArgsConstructor
public class Kapsejladser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "dato", nullable = false)
  private LocalDate dato;

  public Kapsejladser(LocalDate dato) {
    this.dato = dato;
  }
}