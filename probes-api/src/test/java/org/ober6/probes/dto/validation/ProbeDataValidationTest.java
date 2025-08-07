package org.ober6.probes.dto.validation;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.ober6.probes.dto.ProbeData;

public class ProbeDataValidationTest extends BaseValidationTest {

  @Test
  void validProbeData_ShouldPassValidation() {
    ProbeData data = new ProbeData(1, 42.0, System.currentTimeMillis());
    Set<ConstraintViolation<ProbeData>> violations = validator.validate(data);

    assertThat(violations).isEmpty(); // No constraint violations expected
  }
}
