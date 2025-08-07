package org.ober6.probes.dto.validation;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.ober6.probes.dto.Range;
import org.ober6.probes.dto.SensorUpdateData;

public class SensorUpdateDataValidationTest extends BaseValidationTest {

  @Test
  void validSensorUpdateData_ShouldPassValidation() {
    SensorUpdateData data =
        new SensorUpdateData(1, new Range(0.0, 100.0), new String[] {"a@b.com"});
    Set<ConstraintViolation<SensorUpdateData>> violations = validator.validate(data);

    assertThat(violations).isEmpty(); // All fields are valid
  }

  @Test
  void invalidId_ShouldFailValidation() {
    SensorUpdateData data =
        new SensorUpdateData(0, new Range(0.0, 100.0), new String[] {"a@b.com"});
    Set<ConstraintViolation<SensorUpdateData>> violations = validator.validate(data);

    assertThat(violations)
        .anyMatch(
            v ->
                v.getPropertyPath().toString().equals("id")
                    && v.getMessage().equals("Sensor ID must be >= 1"));
  }
}
