package com.example.kapsejlbackend.service;

import com.example.kapsejlbackend.model.Kapsejladser;
import com.example.kapsejlbackend.repository.KapsejladserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KapsejladserService {
  private final KapsejladserRepository kapsejladserRepository;
  @Autowired
  public KapsejladserService(KapsejladserRepository kapsejladserRepository) {
    this.kapsejladserRepository = kapsejladserRepository;
  }
  public List<Kapsejladser> getAllKapsejladser() {
    return kapsejladserRepository.findAll();
  }
  public Kapsejladser getKapsejladserById(Long id) {
    Optional<Kapsejladser> kapsejladser = kapsejladserRepository.findById(id);
    return kapsejladser.orElse(null);
  }
  public Kapsejladser createKapsejladser(Kapsejladser kapsejladser) {
    return kapsejladserRepository.save(kapsejladser);
  }
  public Kapsejladser updateKapsejladser(Long id, Kapsejladser kapsejladser) {
    Optional<Kapsejladser> existingKapsejladser = kapsejladserRepository.findById(id);
    if (existingKapsejladser.isPresent()) {
      Kapsejladser updatedKapsejladser = existingKapsejladser.get();
      updatedKapsejladser.setDato(kapsejladser.getDato());
      return kapsejladserRepository.save(updatedKapsejladser);
    }
    return null;
  }
  public void deleteKapsejladser(Long id) {
    kapsejladserRepository.deleteById(id);
  }
}