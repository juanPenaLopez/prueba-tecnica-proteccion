package com.prueba.tecnica.proteccion.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@Entity
@Table(name="SERIE_FIBONACCI")
@Data
public class SerieFibonacciEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "hora_fibonacci")
    private LocalTime horaFibonacci;

    @Column(name = "serie_fibonacci")
    private String serieFibonacci;
}
