package com.example.kapsejlbackend.service;

import com.example.kapsejlbackend.model.Deltagere;
import com.example.kapsejlbackend.repository.DeltagereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeltagereService {
  private final DeltagereRepository deltagereRepository;
  @Autowired
  public DeltagereService(DeltagereRepository deltagereRepository) {
    this.deltagereRepository = deltagereRepository;
  }
  public List<Deltagere> getAllDeltagere() {
    return deltagereRepository.findAll();
  }

  public Deltagere getDeltagereById(Long id) {
    Optional<Deltagere> deltagere = deltagereRepository.findById(id);
    return deltagere.orElse(null);
  }

  public Deltagere createDeltagere(Deltagere deltagere) {
    return deltagereRepository.save(deltagere);
  }
  public Deltagere updateDeltagere(Long id, Deltagere deltagere) {
    Optional<Deltagere> existingDeltagere = deltagereRepository.findById(id);
    if (existingDeltagere.isPresent()) {
      Deltagere updatedDeltagere = existingDeltagere.get();
      updatedDeltagere.setKaptajnsNavn(deltagere.getKaptajnsNavn());
      updatedDeltagere.setSejlbaade(deltagere.getSejlbaade());
      updatedDeltagere.setKapsejladser(deltagere.getKapsejladser());
      updatedDeltagere.setPoint(deltagere.getPoint());
      return deltagereRepository.save(updatedDeltagere);
    }
    return null;
  }
  public void deleteDeltagere(Long id) {
    deltagereRepository.deleteById(id);
  }
}

