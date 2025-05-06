package com.prueba.tecnica.proteccion.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class SerieFibonacciDTO {

    private Long id;
    private LocalTime horaFibonacci;
    private String serieFibonacci;
}
