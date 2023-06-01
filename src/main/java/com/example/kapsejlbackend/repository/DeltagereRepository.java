package com.example.kapsejlbackend.repository;

import com.example.kapsejlbackend.model.Deltagere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeltagereRepository extends JpaRepository<Deltagere, Long> {
}