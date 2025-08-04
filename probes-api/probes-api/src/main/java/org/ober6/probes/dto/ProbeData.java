package org.ober6.probes.dto;

import jakarta.validation.constraints.Min;
import org.ober6.probes.messages.ErrorMessages;

public record ProbeData(
    @Min(value = 1, message = ErrorMessages.WRONG_SENSOR_ID) long id,
    double value,
    long timestamp) {}
