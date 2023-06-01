package com.example.kapsejlbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sejlbaade")
@Data
@NoArgsConstructor
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

  @Transient
  private String baadTypeDisplayName;
  public void setBaadTypeDisplayName(String baadTypeDisplayName) {
    this.baadTypeDisplayName = baadTypeDisplayName;
  }
}
