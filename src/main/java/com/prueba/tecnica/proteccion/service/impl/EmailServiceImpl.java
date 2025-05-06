package com.prueba.tecnica.proteccion.service.impl;

import com.prueba.tecnica.proteccion.service.EmailService;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    private static final Logger logger = ESAPI.getLogger(EmailServiceImpl.class);

    private static final String[] DESTINATARIOS = {
            "didier.correa@proteccion.com.co",
            "correalondon@gmail.com"
    };

    /**
     * Constructor de la clase
     * @param mailSender
     */
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Método encargado de enviar el correo con la serie de fibonacci,
     * además, de agregarle el asunto
     * @param serie
     * @param asunto
     */
    @Override
    public void enviarCorreoSerieFibonnaci(String serie, String asunto) {

        logger.info(Logger.EVENT_SUCCESS, "Ingreso a metodo enviarCorreoSerieFibonnaci");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(DESTINATARIOS);
        message.setSubject(asunto);
        message.setText("Resultado de la serie Fibonacci:\n\n" + serie);

        mailSender.send(message);

        logger.info(Logger.EVENT_SUCCESS, "Finalizo metodo enviarCorreoSerieFibonnaci");
    }
}
