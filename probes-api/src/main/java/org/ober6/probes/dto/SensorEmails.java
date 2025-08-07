package org.ober6.probes.dto;

import static org.ober6.probes.messages.ErrorMessages.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SensorEmails(
    @Min(value = 1, message = WRONG_SENSOR_ID) long id,
    @NotNull(message = MISSING_EMAILS) String[] emails) {}
