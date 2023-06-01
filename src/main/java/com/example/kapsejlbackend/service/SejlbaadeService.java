package com.example.kapsejlbackend.service;

import com.example.kapsejlbackend.model.Kapsejladser;
import com.example.kapsejlbackend.model.Sejlbaade;
import com.example.kapsejlbackend.repository.KapsejladserRepository;
import com.example.kapsejlbackend.repository.SejlbaadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SejlbaadeService {

  private final SejlbaadeRepository sejlbaadeRepository;
  private final KapsejladserRepository kapsejladserRepository;

  @Autowired
  public SejlbaadeService(SejlbaadeRepository sejlbaadeRepository, KapsejladserRepository kapsejladserRepository) {
    this.sejlbaadeRepository = sejlbaadeRepository;
    this.kapsejladserRepository = kapsejladserRepository;
  }

  public List<Sejlbaade> getAllSejlbaade() {
    List<Sejlbaade> sejlbaadeList = sejlbaadeRepository.findAll();
    for (Sejlbaade sejlbaade : sejlbaadeList) {
      sejlbaade.setBaadTypeDisplayName(sejlbaade.getBaadType().getDisplayName());
    }
    return sejlbaadeList;
  }


  public Sejlbaade getSejlbaadeById(Long id) {
    Optional<Sejlbaade> sejlbaade = sejlbaadeRepository.findById(id);
    return sejlbaade.orElse(null);
  }

  public Sejlbaade createSejlbaade(Sejlbaade sejlbaade) {
    System.out.println("SejlbaadeService.createSejlbaade: " + sejlbaade);
    return sejlbaadeRepository.save(sejlbaade);
  }

  public Sejlbaade updateSejlbaade(Long id, Sejlbaade sejlbaade) {
    Optional<Sejlbaade> existingSejlbaade = sejlbaadeRepository.findById(id);
    if (existingSejlbaade.isPresent()) {
      Sejlbaade updatedSejlbaade = existingSejlbaade.get();
      updatedSejlbaade.setBaadNavn(sejlbaade.getBaadNavn());
      updatedSejlbaade.setBaadType(sejlbaade.getBaadType());
      updatedSejlbaade.setPoint(sejlbaade.getPoint());
      return sejlbaadeRepository.save(updatedSejlbaade);
    }
    return null;
  }

  public void deleteSejlbaade(Long id) {
    sejlbaadeRepository.deleteById(id);
  }
  public void setKapsejladsForSejlbaade(Long sejlbaadeId, Long kapsejladsId) {
    Sejlbaade sejlbaade = sejlbaadeRepository.findById(sejlbaadeId).orElseThrow(() -> new IllegalArgumentException("Sejlbaade not found"));
    Kapsejladser kapsejlads = kapsejladserRepository.findById(kapsejladsId).orElseThrow(() -> new IllegalArgumentException("Kapsejlads not found"));

    sejlbaade.setKapsejladser(kapsejlads);
    sejlbaadeRepository.save(sejlbaade);
  }
}

