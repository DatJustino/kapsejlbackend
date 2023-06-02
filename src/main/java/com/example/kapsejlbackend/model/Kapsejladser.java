package com.example.kapsejlbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

  @OneToMany(mappedBy = "kapsejlads", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RaceTime> raceTimes = new ArrayList<>();

  public Kapsejladser(LocalDate dato) {
    this.dato = dato;
  }

  public void addRaceTime(RaceTime raceTime) {
    raceTimes.add(raceTime);
    raceTime.setKapsejlads(this);
  }
}
