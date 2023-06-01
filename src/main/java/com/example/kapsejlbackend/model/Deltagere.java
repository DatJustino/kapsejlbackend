package com.example.kapsejlbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "deltagere")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Deltagere {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "kaptajnsnavn", nullable = false)
  private String kaptajnsNavn;

  @OneToOne
  @JoinColumn(name = "sejlbaade_id")
  private Sejlbaade sejlbaade;

  @ManyToOne
  @JoinColumn(name = "kapsejladser_id", nullable = false)
  private Kapsejladser kapsejladser;

  @Column(name = "point", nullable = false)
  private int point;

  public Deltagere(String kaptajnsNavn, Sejlbaade sejlbade, Kapsejladser kapsejladser, int point) {
    this.kaptajnsNavn = kaptajnsNavn;
    this.sejlbaade = sejlbade;
    this.kapsejladser = kapsejladser;
    this.point = point;
  }
}
