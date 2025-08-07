package org.ober6.probes.dto.validation;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.ober6.probes.dto.SensorRange;

public class SensorRangeValidationTest extends BaseValidationTest {

  @Test
  void nullRange_ShouldFailValidation() {
    SensorRange data = new SensorRange(1, null);
    Set<ConstraintViolation<SensorRange>> violations = validator.validate(data);

    assertThat(violations)
        .anyMatch(
            v ->
                v.getPropertyPath().toString().equals("range")
                    && v.getMessage().equals("Missing range values for sensor"));
  }
}
