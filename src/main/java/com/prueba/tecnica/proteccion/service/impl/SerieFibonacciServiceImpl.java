package com.prueba.tecnica.proteccion.service.impl;

import com.prueba.tecnica.proteccion.dto.SerieFibonacciDTO;
import com.prueba.tecnica.proteccion.dto.SerieFibonacciInDTO;
import com.prueba.tecnica.proteccion.mappers.SerieFibonacciMapper;
import com.prueba.tecnica.proteccion.repository.SerieFibonacciRepository;
import com.prueba.tecnica.proteccion.service.EmailService;
import com.prueba.tecnica.proteccion.service.SerieFibonacciService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class SerieFibonacciServiceImpl implements SerieFibonacciService {

    private SerieFibonacciRepository serieFibonacciRepository;
    private static final Logger logger = ESAPI.getLogger(SerieFibonacciServiceImpl.class);
    private EmailService emailService;

    /**
     * Método encargado de obtener todas las series de fibonacci almacenadas
     * @return Lista con las series de fibonacci
     */
    @Override
    public List<SerieFibonacciDTO> getAllSerieFibonacci() {
        logger.info(Logger.EVENT_SUCCESS, "Ingreso al metodo getAllSerieFibonacci");
        return SerieFibonacciMapper.INSTANCE.toDtoList(serieFibonacciRepository.findAll());
    }

    /**
     * Método encargado de generar la serie de fibonacci a partir de la hora
     * del sistema
     * @return SerieFibonacciDTO dto con la información de la serie y hora
     */
    @Override
    public SerieFibonacciDTO createSerieFibonacci() {

        logger.info(Logger.EVENT_SUCCESS, "Ingreso al metodo createSerieFibonacci");
        return generarYGuardarSerie(LocalTime.now());
    }

    /**
     * Método encargado de crear la serie de fibonacci a partir de una hora
     * indicada en formato HH:MM:SS
     * @param serieFibonacciDTO
     * @return SerieFibonacciDTO
     */
    @Override
    public SerieFibonacciDTO createSerieFibonacciByHour(SerieFibonacciInDTO serieFibonacciDTO) {

        logger.info(Logger.EVENT_SUCCESS, "Ingreso al metodo createSerieFibonacci");

        if(!serieFibonacciDTO.getHora().matches("^(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$")){
            throw new IllegalArgumentException("Formato y/o hora no valido");
        }

        try {
            LocalTime hora = LocalTime.parse(serieFibonacciDTO.getHora());
            return generarYGuardarSerie(hora);
        } catch (DateTimeParseException e) {
            logger.error(Logger.EVENT_FAILURE, e.getMessage());
            throw new IllegalArgumentException("No se pudo convertir la hora.", e);
        }
    }

    /**
     * Método encargado de generar la serie del fibonacci
     * de acuerdo al enunciado 1
     * @param semilla1
     * @param semilla2
     * @param cantidadNumeros
     * @return serieFibonacciNumeros lista con la serie de forma descendente
     */
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

    /**
     * Método encargado de generar y guardar la serie de fibonacci.
     * Adicionalmente, envia correo con la respuesta.
     * @param hora
     * @return dto
     */
    private SerieFibonacciDTO generarYGuardarSerie(LocalTime hora) {
        int semilla1 = hora.getMinute() / 10;
        int semilla2 = hora.getSecond() % 10;
        int cantidadNumeros = hora.getSecond();

        List<Long> serie = generarSerieFibonacci(semilla1, semilla2, cantidadNumeros);
        Collections.reverse(serie);

        SerieFibonacciDTO dto = SerieFibonacciDTO.builder()
                .serieFibonacci(serie.toString())
                .horaFibonacci(hora)
                .build();

        serieFibonacciRepository.save(SerieFibonacciMapper.INSTANCE.getEntityFromDTO(dto));
        emailService.enviarCorreoSerieFibonnaci(dto.getSerieFibonacci(), "Prueba Técnica - Juan Pablo Peña Lopez");

        logger.info(Logger.EVENT_SUCCESS, "Finalizó creación de serie Fibonacci para hora: " + hora);
        return dto;
    }
}
