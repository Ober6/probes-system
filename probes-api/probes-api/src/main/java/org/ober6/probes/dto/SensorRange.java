package org.ober6.probes.dto;

import static org.ober6.probes.messages.ErrorMessages.MISSING_RANGE;
import static org.ober6.probes.messages.ErrorMessages.WRONG_SENSOR_ID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SensorRange(
    @Min(value = 1, message = WRONG_SENSOR_ID) long id,
    @NotNull(message = MISSING_RANGE) Range range) {}
