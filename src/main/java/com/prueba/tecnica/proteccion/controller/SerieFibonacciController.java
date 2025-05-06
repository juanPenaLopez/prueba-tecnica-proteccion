package com.prueba.tecnica.proteccion.controller;

import com.prueba.tecnica.proteccion.dto.SerieFibonacciDTO;
import com.prueba.tecnica.proteccion.service.SerieFibonacciService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/private/serie-fibonacci")
public class SerieFibonacciController {

    private final SerieFibonacciService serieFibonacciService;

    @Operation(summary = "Obtiene todas las series de fibonacci almacenadas")
    @GetMapping(path = "/getAllSerieFibonacci", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SerieFibonacciDTO>> getAllSerieFibonacci() {
        return new ResponseEntity<>(serieFibonacciService.getAllSerieFibonacci(), new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Genera una nueva serie de fibonacci a partir de la hora actual")
    @GetMapping(path = "/createSerieFibonacci", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SerieFibonacciDTO> createSerieFibonacci() {
        return new ResponseEntity<>(serieFibonacciService.createSerieFibonacci(), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(path = "/getSerieFibonacci", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SerieFibonacciDTO> createSerieFibonacci(@RequestBody SerieFibonacciDTO serieFibonacciDTO) {
        return new ResponseEntity<>(serieFibonacciService.createSerieFibonacci(), new HttpHeaders(), HttpStatus.OK);
    }
}
