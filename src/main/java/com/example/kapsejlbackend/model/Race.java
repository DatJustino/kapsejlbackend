package com.example.kapsejlbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "races")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Race {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date", nullable = false)
  private LocalDate date;

  @Column(name = "time", nullable = false)
  private LocalTime raceTime;

  @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RaceResults> raceResults = new ArrayList<>();

  @ManyToMany(mappedBy = "races")
  private List<Sailboat> sailboats = new ArrayList<>();

  @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Participant> participants = new ArrayList<>();

  public Race(LocalDate date, LocalTime raceTime) {
    this.date = date;
    this.raceTime = raceTime;
  }

  public void addRaceResult(RaceResults raceResult) {
    raceResults.add(raceResult);
    raceResult.setRace(this);
  }

  public void removeRaceResult(RaceResults raceResult) {
    raceResults.remove(raceResult);
    raceResult.setRace(null);
  }

  public void addParticipant(Participant participant) {
    participants.add(participant);
    participant.setRace(this);
  }

  public void removeParticipant(Participant participant) {
    participants.remove(participant);
    participant.setRace(null);
  }
}