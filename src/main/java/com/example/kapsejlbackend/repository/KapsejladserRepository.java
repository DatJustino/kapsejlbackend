package com.example.kapsejlbackend.repository;


import com.example.kapsejlbackend.model.Kapsejladser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KapsejladserRepository extends JpaRepository<Kapsejladser, Long> {
  List<Kapsejladser> findAllByOrderByDatoAscTimeAsc();
}