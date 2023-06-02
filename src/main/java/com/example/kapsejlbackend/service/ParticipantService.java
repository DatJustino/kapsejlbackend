package com.example.kapsejlbackend.service;

import com.example.kapsejlbackend.model.Participant;
import com.example.kapsejlbackend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

  private final ParticipantRepository participantRepository;

  @Autowired
  public ParticipantService(ParticipantRepository participantRepository) {
    this.participantRepository = participantRepository;
  }

  public List<Participant> getAllParticipants() {
    return participantRepository.findAll();
  }

  public Participant getParticipantsById(Long id) {
    Optional<Participant> participant = participantRepository.findById(id);
    return participant.orElse(null);
  }

  public Participant createParticipant(Participant participant) {
    return participantRepository.save(participant);
  }

  public Participant updateParticipants(Long id, Participant participant) {
    Optional<Participant> existingParticipant = participantRepository.findById(id);
    if (existingParticipant.isPresent()) {
      Participant updatedParticipant = existingParticipant.get();
      updatedParticipant.setCaptainsName(participant.getCaptainsName());
      updatedParticipant.setSailBoat(participant.getSailBoat());
      updatedParticipant.setRace(participant.getRace());
      return participantRepository.save(updatedParticipant);
    }
    return null;
  }

  public void deleteParticipant(Long id) {
    participantRepository.deleteById(id);
  }
  public List<Participant> createMultipleParticipants(List<Participant> participantList) {
    return participantRepository.saveAll(participantList);
  }
}

