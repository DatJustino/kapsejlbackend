package com.example.kapsejlbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deltagere")
@Data
@NoArgsConstructor
public class Deltagere {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "sejlbaade_id", nullable = false)
  private Sejlbaade sejlbaade;

  @ManyToOne
  @JoinColumn(name = "kapsejladser_id", nullable = false)
  private Kapsejladser kapsejladser;

  @Column(name = "point", nullable = false)
  private int point;

  public Deltagere(Sejlbaade sejlbade, Kapsejladser kapsejladser, int point) {
    this.sejlbaade = sejlbade;
    this.kapsejladser = kapsejladser;
    this.point = point;
  }
}
