package com.example.kapsejlbackend.repository;

import com.example.kapsejlbackend.model.RaceResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceResultsRepository extends JpaRepository<RaceResults, Long> {
}