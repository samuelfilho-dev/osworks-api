package com.albino.tecnologia.osworks.infra.jackson.serializer;

import com.albino.tecnologia.osworks.infra.utils.JavaTimeUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

    @Serial
    private static final long serialVersionUID = -5009399149937231913L;

    public LocalDateSerializer() {
        super(LocalDate.class);
    }

    @Override
    public void serialize(final LocalDate value, final JsonGenerator generator, final SerializerProvider provider) throws IOException {
        generator.writeString(value.format(JavaTimeUtils.LOCAL_DATE_FORMATTER));
    }

}
