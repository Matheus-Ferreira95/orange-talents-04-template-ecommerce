package br.com.zupacademy.matheus.mercadolivre.compartilhado.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FieldMessage> erroDeValidacao(MethodArgumentNotValidException ex) {
        List<FieldMessage> list = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            list.add(new FieldMessage(error.getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale())));
        }
        return list;
    }
}
