package com.example.kapsejlbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "participants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Participant {
  public Participant(String captainsName, Sailboat sailBoat, Race race) {
    this.captainsName = captainsName;
    this.sailBoat = sailBoat;
    this.race = race;
  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "participantid", nullable = false)
  private Long id;

  @Column(name = "captainsname")
  private String captainsName;

  @OneToOne
  @JoinColumn(name = "sailboat_id")
  @JsonIgnoreProperties("participant")
  private Sailboat sailBoat;

  @ManyToOne
  @JoinColumn(name = "race_id", nullable = false)
  @JsonIgnoreProperties("participants")
  private Race race;
}