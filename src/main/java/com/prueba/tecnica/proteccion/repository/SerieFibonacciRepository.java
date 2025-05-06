package com.prueba.tecnica.proteccion.repository;

import com.prueba.tecnica.proteccion.entity.SerieFibonacciEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieFibonacciRepository extends JpaRepository<SerieFibonacciEntity, Long> {


}
