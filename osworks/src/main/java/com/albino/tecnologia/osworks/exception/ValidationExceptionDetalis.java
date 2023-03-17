package com.albino.tecnologia.osworks.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetalis extends ExceptionDetalis {

    private final String campo;
    private final String campoDeMensagem;
}
