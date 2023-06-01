package com.example.kapsejlbackend.repository;

import com.example.kapsejlbackend.model.Sejlbaade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SejlbadeRepository extends JpaRepository<Sejlbaade, Long> {
}