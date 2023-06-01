package com.example.kapsejlbackend.repository;

import com.example.kapsejlbackend.model.BaadType;
import com.example.kapsejlbackend.model.Sejlbaade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SejlbaadeRepository extends JpaRepository<Sejlbaade, Long> {
  List<Sejlbaade> findByBaadType(BaadType boatType);
}