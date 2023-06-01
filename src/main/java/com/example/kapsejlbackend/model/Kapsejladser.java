package com.example.kapsejlbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "kapsejladser")
@Data
@NoArgsConstructor
@Getter
@Setter
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