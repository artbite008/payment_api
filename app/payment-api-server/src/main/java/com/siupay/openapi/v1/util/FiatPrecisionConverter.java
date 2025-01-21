package com.siupay.openapi.v1.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.siupay.openapi.constant.DynamicConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Slf4j
@Component
public class FiatPrecisionConverter extends JsonSerializer {

    private static DynamicConstants dynamicConstants;

    @Autowired
    public void setDynamicConstants(DynamicConstants dynamicConstants) {
        FiatPrecisionConverter.dynamicConstants = dynamicConstants;
    }

    public FiatPrecisionConverter() {
    }

    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(this.setPrecision((BigDecimal) value, dynamicConstants.getFiatPrecision(), RoundingMode.DOWN));
    }

    public BigDecimal setPrecision(BigDecimal value, Integer fiatScale, RoundingMode roundingMode) {
        return Objects.isNull(value) ? BigDecimal.ZERO : value.setScale(fiatScale, roundingMode);
    }
}
