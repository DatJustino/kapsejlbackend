package com.example.kapsejlbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sejlbaade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sejlbaade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "baadnavn", nullable = false)
  private String baadNavn;

  @Enumerated(EnumType.STRING)
  @Column(name = "baadtype", nullable = false)
  private BaadType baadType;

  @OneToOne(mappedBy = "sejlbaade", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("sejlbaade")
  private Deltagere deltagere;

  @Column(name = "point", nullable = false)
  private int point;

  public void setKapsejladser(Kapsejladser kapsejlads) {
    if (this.deltagere == null) {
      this.deltagere = new Deltagere();
    }
    this.deltagere.setKapsejladser(kapsejlads);
  }

  @Transient
  private String baadTypeDisplayName;

  public void setBaadTypeDisplayName(String baadTypeDisplayName) {
    this.baadTypeDisplayName = baadTypeDisplayName;
  }
}
