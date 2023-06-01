package com.example.kapsejlbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

  @Column(name = "time", nullable = false)
  private LocalTime time;

  public Kapsejladser(LocalDate dato, LocalTime time) {
    this.dato = dato;
    this.time = time;
  }
  public LocalDate getDate() {
    return this.dato;
  }

  @OneToMany(mappedBy = "kapsejladser", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Deltagere> deltagere = new ArrayList<>();


  public void addDeltagere(Deltagere deltagere) {
    this.deltagere.add(deltagere);
    deltagere.setKapsejladser(this);
  }

  public void removeDeltagere(Deltagere deltagere) {
    this.deltagere.remove(deltagere);
    deltagere.setKapsejladser(null);
  }
}
