package org.ober6.probes.dto.validation;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.ober6.probes.dto.SensorEmails;

public class SensorEmailsValidationTest extends BaseValidationTest {

  @Test
  void nullEmails_ShouldFailValidation() {
    SensorEmails data = new SensorEmails(1, null);
    Set<ConstraintViolation<SensorEmails>> violations = validator.validate(data);

    assertThat(violations).isNotEmpty(); // Expect failure due to missing email list
  }
}
