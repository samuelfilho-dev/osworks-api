package com.albino.tecnologia.osworks.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetalis {
    protected String titulo;

    protected Integer status;
    protected String detalhes;
    protected String mensagemDesenvolvedor;
    protected LocalDateTime horario;
}
