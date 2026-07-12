package com.luishenrique.cap.Historico_Vacinacao.handler;

import com.luishenrique.cap.Historico_Vacinacao.exception.BadRequestException;
import com.luishenrique.cap.Historico_Vacinacao.exception.ErroResponse;
import com.luishenrique.cap.Historico_Vacinacao.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResponse handlerNotFoundException(NotFoundException e){
        return new ErroResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResponse handlerBadRequestException(BadRequestException e){
        return new ErroResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResponse handlerException(Exception e){
        return new ErroResponse(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
    }

}
