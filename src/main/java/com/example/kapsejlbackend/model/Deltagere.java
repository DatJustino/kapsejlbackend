package com.example.kapsejlbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deltagere")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
  @JsonIgnoreProperties("deltagere")
  private Sejlbaade sejlbaade;

  @ManyToOne
  @JoinColumn(name = "kapsejladser_id", nullable = false)
  @JsonIgnoreProperties("deltagere")
  private Kapsejladser kapsejladser;

  public Deltagere(String kaptajnsNavn, Sejlbaade sejlbaade, Kapsejladser kapsejladser) {
    this.kaptajnsNavn = kaptajnsNavn;
    this.sejlbaade = sejlbaade;
    this.kapsejladser = kapsejladser;
  }
}
