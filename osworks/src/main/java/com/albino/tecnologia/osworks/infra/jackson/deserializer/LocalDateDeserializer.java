package com.albino.tecnologia.osworks.infra.jackson.deserializer;

import com.albino.tecnologia.osworks.infra.utils.JavaTimeUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    @Serial
    private static final long serialVersionUID = 2516705754848708776L;

    public LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
        return LocalDate.parse(parser.readValueAs(String.class), JavaTimeUtils.LOCAL_DATE_FORMATTER);
    }
}
