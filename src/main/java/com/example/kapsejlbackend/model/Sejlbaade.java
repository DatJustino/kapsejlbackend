package com.example.kapsejlbackend.model;

import com.example.kapsejlbackend.model.BaadType;
import com.example.kapsejlbackend.model.Deltagere;
import com.example.kapsejlbackend.model.RaceTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sejlbaade")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sejlbaade {
  public Sejlbaade(String baadNavn, BaadType baadType) {
    this.baadNavn = baadNavn;
    this.baadType = baadType;
  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "baadnavn", nullable = false)
  private String baadNavn;

  @Enumerated(EnumType.STRING)
  @Column(name = "baadtype", nullable = false)
  private BaadType baadType;

  @JsonIgnore
  @OneToOne(mappedBy = "sejlbaade", cascade = CascadeType.ALL)
  private Deltagere deltagere;

  @OneToMany(mappedBy = "sejlbaade", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RaceTime> raceTimes;

  @Transient
  private String baadTypeDisplayName;

  public void setBaadTypeDisplayName(String baadTypeDisplayName) {
    this.baadTypeDisplayName = baadTypeDisplayName;
  }
}
