package com.example.kapsejlbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "racetimes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RaceTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "time", nullable = false)
  private LocalTime time;

  @ManyToOne
  @JoinColumn(name = "sejlbaade_id", nullable = false)
  private Sejlbaade sejlbaade;

  @ManyToOne
  @JoinColumn(name = "kapsejladser_id", nullable = false)
  private Kapsejladser kapsejlads;

  public RaceTime(LocalTime time, Sejlbaade sejlbaade, Kapsejladser kapsejlads) {
    this.time = time;
    this.sejlbaade = sejlbaade;
    this.kapsejlads = kapsejlads;
  }
}

