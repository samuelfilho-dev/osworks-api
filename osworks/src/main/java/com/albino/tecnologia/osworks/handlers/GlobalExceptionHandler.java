package com.albino.tecnologia.osworks.handlers;

import com.albino.tecnologia.osworks.exceptions.BadRequestException;
import com.albino.tecnologia.osworks.exceptions.BadRequestExceptionDetails;
import com.albino.tecnologia.osworks.exceptions.ExceptionDetails;
import com.albino.tecnologia.osworks.exceptions.ValidationExceptionDetails;
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
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Please Check the documentation")
                        .details(ex.getLocalizedMessage())
                        .developerMessage(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String errorsFields = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Invalided Fields")
                        .details(ex.getMessage())
                        .developerMessage(ex.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(errorsFields)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDetails> handlerNullPointerException(NullPointerException ex) {

        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Houve Campos Nulos")
                        .details("Por Favor, Confira o(s) campo(s)")
                        .developerMessage(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDetails> handlerDataIntegrityViolationException(
            DataIntegrityViolationException ex) {

        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Violação, Já existe um registro com esse valor")
                        .details(ex.getLocalizedMessage())
                        .developerMessage(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ExceptionDetails> handlerUnexpectedTypeException(
            UnexpectedTypeException ex) {

        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Verifique a Tipagem do Dado")
                        .details(ex.getLocalizedMessage())
                        .developerMessage(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionDetails> handlerNoSuchElementException(
            NoSuchElementException ex) {

        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Valor Não Encontrado")
                        .details(ex.getLocalizedMessage())
                        .developerMessage(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timeStamp(LocalDateTime.now())
                .status(status.value())
                .title(ex.getCause().getMessage())
                .details(ex.getLocalizedMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }



}
