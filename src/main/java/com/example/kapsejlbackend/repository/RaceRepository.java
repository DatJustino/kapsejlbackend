package com.example.kapsejlbackend.repository;


import com.example.kapsejlbackend.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
/*
  List<Race> findAllByOrderByDateAscTimeAsc();
*/
}