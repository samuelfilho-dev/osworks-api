package com.albino.tecnologia.osworks.handler;

import com.albino.tecnologia.osworks.exception.BadResquestException;
import com.albino.tecnologia.osworks.exception.BadResquestExceptionDetails;
import com.albino.tecnologia.osworks.exception.ExceptionDetalis;
import com.albino.tecnologia.osworks.exception.ValidationExceptionDetalis;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.UnexpectedTypeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadResquestException.class)
    public ResponseEntity<BadResquestExceptionDetails> handleBadResquestException(BadResquestException ex) {
        return new ResponseEntity<>(
                BadResquestExceptionDetails.builder()
                        .horario(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Bad Request Exception, Por Favor Verifique a documentação")
                        .detalhes(ex.getLocalizedMessage())
                        .mensagemDesenvolvedor(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String campos = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String errosDoCampos = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetalis.builder()
                        .horario(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Bad Request Exception, Campos Invalidos")
                        .detalhes(ex.getMessage())
                        .mensagemDesenvolvedor(ex.getClass().getName())
                        .campo(campos)
                        .campoDeMensagem(errosDoCampos)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDetalis> handlerNullPointerException(NullPointerException ex) {

        return new ResponseEntity<>(
                ExceptionDetalis.builder()
                        .horario(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Bad Request Exception, Houve Campos Nulos")
                        .detalhes("Por Favor, Confira o(s) campo(s)")
                        .mensagemDesenvolvedor(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDetalis> handlerDataIntegrityViolationException(
            DataIntegrityViolationException ex) {

        return new ResponseEntity<>(
                ExceptionDetalis.builder()
                        .horario(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Violação, Já existe um registro com esse valor")
                        .detalhes(ex.getLocalizedMessage())
                        .mensagemDesenvolvedor(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ExceptionDetalis> handlerUnexpectedTypeException(
            UnexpectedTypeException ex) {

        return new ResponseEntity<>(
                ExceptionDetalis.builder()
                        .horario(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Bad Request Exception, Verifique a Tipagem do Dado")
                        .detalhes(ex.getLocalizedMessage())
                        .mensagemDesenvolvedor(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionDetalis> handlerNoSuchElementException(
            NoSuchElementException ex) {

        return new ResponseEntity<>(
                ExceptionDetalis.builder()
                        .horario(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Bad Request Exception, Valor Não Encontrado")
                        .detalhes(ex.getLocalizedMessage())
                        .mensagemDesenvolvedor(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetalis exceptionDetalis = ExceptionDetalis.builder().horario(LocalDateTime.now())
                .status(status.value())
                .titulo(ex.getCause().getMessage())
                .detalhes(ex.getLocalizedMessage())
                .mensagemDesenvolvedor(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetalis, headers, status);
    }



}
