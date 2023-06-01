package com.example.kapsejlbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sejlbade")
@Data
@NoArgsConstructor
public class Sejlbaade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "baadtype", nullable = false)
  private BaadeType baadtype;

  public Sejlbaade(BaadeType baadtype) {
    this.baadtype = baadtype;

  }
}
