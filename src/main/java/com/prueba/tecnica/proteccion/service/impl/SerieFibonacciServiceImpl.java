package com.prueba.tecnica.proteccion.service.impl;

import com.prueba.tecnica.proteccion.dto.SerieFibonacciDTO;
import com.prueba.tecnica.proteccion.mappers.SerieFibonacciMapper;
import com.prueba.tecnica.proteccion.repository.SerieFibonacciRepository;
import com.prueba.tecnica.proteccion.service.SerieFibonacciService;
import lombok.AllArgsConstructor;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class SerieFibonacciServiceImpl implements SerieFibonacciService {

    private SerieFibonacciRepository serieFibonacciRepository;
    private static final Logger logger = ESAPI.getLogger(SerieFibonacciServiceImpl.class);

    @Override
    public List<SerieFibonacciDTO> getAllSerieFibonacci() {
        logger.info(Logger.EVENT_SUCCESS, "Ingreso al metodo getAllSerieFibonacci");
        return SerieFibonacciMapper.INSTANCE.toDtoList(serieFibonacciRepository.findAll());
    }

    @Override
    public SerieFibonacciDTO createSerieFibonacci() {

        logger.info(Logger.EVENT_SUCCESS, "Ingreso al metodo createSerieFibonacci");

        LocalTime now = LocalTime.now();

        Integer semilla1 = now.getMinute() / 10;
        Integer semilla2 = now.getSecond() % 10;

        String segundos = String.format("%02d", now.getSecond());
        Integer cantidadNumeros = Integer.parseInt(segundos);

        List<Long> serieFibonacciNumeros = generarSerieFibonacci(semilla1, semilla2,
                cantidadNumeros);

        Collections.reverse(serieFibonacciNumeros);

        SerieFibonacciDTO serieFibonacciDTO = SerieFibonacciDTO.builder()
                .serieFibonacci(serieFibonacciNumeros.toString())
                .horaFibonacci(now)
                .build();

        serieFibonacciRepository.save(SerieFibonacciMapper.INSTANCE.getEntityFromDTO(serieFibonacciDTO));

        logger.info(Logger.EVENT_SUCCESS, "Finalizo metodo getAllSerieFibonacci");

        return serieFibonacciDTO;
    }

    private static List<Long> generarSerieFibonacci(Integer semilla1, Integer semilla2, Integer cantidadNumeros) {

        logger.info(Logger.EVENT_SUCCESS, "Ingreso al metodo generarSerieFibonacci con los parametros: "
            + semilla1 + "," + semilla2 + "," + cantidadNumeros);

        List<Long> serieFibonacciNumeros = new ArrayList<>();
        serieFibonacciNumeros.add(Long.valueOf(semilla1));
        serieFibonacciNumeros.add(Long.valueOf(semilla2));

        for(int i = 0; i < cantidadNumeros; i++) {
            Long siguienteNumero = serieFibonacciNumeros.get(serieFibonacciNumeros.size() - 1) +
                    serieFibonacciNumeros.get(serieFibonacciNumeros.size() - 2);
            serieFibonacciNumeros.add(siguienteNumero);
        }

        logger.info(Logger.EVENT_SUCCESS, "Finalizo metodo generarSerieFibonacci");

        return serieFibonacciNumeros;
    }
}
