package com.prueba.tecnica.proteccion.controllers;

import com.prueba.tecnica.proteccion.controller.SerieFibonacciController;
import com.prueba.tecnica.proteccion.dto.SerieFibonacciDTO;
import com.prueba.tecnica.proteccion.service.SerieFibonacciService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SerieFibonacciControllerTest {

    @InjectMocks
    SerieFibonacciController serieFibonacciController;

    @Mock
    SerieFibonacciService serieFibonacciService;

    @Test
    public void getAllSerieFibonacci() {
        SerieFibonacciDTO dto1 = SerieFibonacciDTO.builder()
                .serieFibonacci("[0, 1, 1, 2]")
                .horaFibonacci(LocalTime.of(10, 15, 20))
                .build();

        SerieFibonacciDTO dto2 = SerieFibonacciDTO.builder()
                .serieFibonacci("[8, 5, 3, 2]")
                .horaFibonacci(LocalTime.of(10, 23, 04))
                .build();

        when(serieFibonacciService.getAllSerieFibonacci())
                .thenReturn(List.of(dto1, dto2));

        ResponseEntity<List<SerieFibonacciDTO>> response = serieFibonacciController.getAllSerieFibonacci();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("[0, 1, 1, 2]", response.getBody().get(0).getSerieFibonacci());
    }
}
