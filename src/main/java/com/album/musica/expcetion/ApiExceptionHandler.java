package com.album.musica.expcetion;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler {

    private final MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrosDeValidacao handle(MethodArgumentNotValidException exception) {

        ErrosDeValidacao erros = new ErrosDeValidacao(LocalDateTime.now(),
                "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(field -> {
            String mensagem = messageSource.getMessage(field, LocaleContextHolder.getLocale());
            erros.adiciona(new Erro(field.getField(), mensagem) );
        });

        return erros;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            EntityNotFoundException.class,
            HttpRequestMethodNotSupportedException.class
    })
    public BaseError handleEntityNotFound(Exception ex) {
        return new BaseError(ex.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            SQLException.class
    })
    public BaseError handleBadJSONFormat(Exception ex) {
        return new BaseError(ex.getMessage());
    }

}