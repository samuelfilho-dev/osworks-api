package com.albino.tecnologia.osworks.infra.jackson.serializer;

import com.albino.tecnologia.osworks.infra.utils.JavaTimeUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.io.Serial;
import java.time.LocalDateTime;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    @Serial
    private static final long serialVersionUID = 6854124059821803511L;

    public LocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(final LocalDateTime value, final JsonGenerator generator, final SerializerProvider provider) throws IOException {
        generator.writeString(value.format(JavaTimeUtils.LOCAL_DATE_TIME_FORMATTER));
    }
}
