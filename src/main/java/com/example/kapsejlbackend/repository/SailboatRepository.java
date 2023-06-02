package com.example.kapsejlbackend.repository;

import com.example.kapsejlbackend.model.BoatTypes;
import com.example.kapsejlbackend.model.Sailboat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SailboatRepository extends JpaRepository<Sailboat, Long> {
  List<Sailboat> findByBoatTypes(BoatTypes boatTypes);
}