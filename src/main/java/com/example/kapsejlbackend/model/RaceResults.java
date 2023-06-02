package com.example.kapsejlbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "raceresults")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RaceResults {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "participantid", nullable = false)
  private Long participantId;

  @Column(name = "boatid", nullable = false)
  private Long boatId;

  @Column(name = "elapsedtime", nullable = false)
  private int elapsedTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "race_id")
  private Race race;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sailboat_id")
  private Sailboat sailboat;
}