package com.example.kapsejlbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "sailboats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sailboat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "boatname", nullable = false)
  private String boatName;

  @Enumerated(EnumType.STRING)
  @Column(name = "boattype", nullable = false)
  private BoatTypes boatTypes;

  @OneToOne(mappedBy = "sailBoat", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("sailBoat")
  private Participant participant;

  @Column(name = "point", nullable = false)
  private int point;

  public void setRace(Race race) {
    if (this.participant == null) {
      this.participant = new Participant();
    }
    this.participant.setRace(race);
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "sailboat_race",
      joinColumns = @JoinColumn(name = "sailboat_id"),
      inverseJoinColumns = @JoinColumn(name = "race_id"))
  private List<Race> races = new ArrayList<>();

  public List<RaceResults> getRaceResults() {
    List<RaceResults> raceResults = new ArrayList<>();
    for (Race race : races) {
      raceResults.addAll(race.getRaceResults());
    }
    return raceResults;
  }

  @Transient
  private int standing;

  @Transient
  private String boatDisplayName;

  public void setBoatDisplayName(String boatDisplayName) {
    this.boatDisplayName = boatDisplayName;
  }
}