package com.siupay.openapi.v1.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * PA-2530
 */
@Slf4j
@Component
public class BigDecimalCommonConverter extends JsonSerializer {

    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(Objects.nonNull(value)){
            jsonGenerator.writeNumber(((BigDecimal) value).stripTrailingZeros().toPlainString());
        }
    }

}
