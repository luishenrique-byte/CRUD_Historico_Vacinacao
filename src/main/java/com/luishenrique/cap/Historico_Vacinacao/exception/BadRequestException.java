package com.luishenrique.cap.Historico_Vacinacao.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
