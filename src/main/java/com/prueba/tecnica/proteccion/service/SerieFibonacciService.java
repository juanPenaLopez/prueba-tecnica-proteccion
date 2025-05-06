package com.prueba.tecnica.proteccion.service;

import com.prueba.tecnica.proteccion.dto.SerieFibonacciDTO;
import com.prueba.tecnica.proteccion.dto.SerieFibonacciInDTO;

import java.util.List;

public interface SerieFibonacciService {

    List<SerieFibonacciDTO> getAllSerieFibonacci();

    SerieFibonacciDTO createSerieFibonacci();

    SerieFibonacciDTO createSerieFibonacciByHour(SerieFibonacciInDTO serieFibonacciDTO);
}
