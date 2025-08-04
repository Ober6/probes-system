package org.ober6.probes.dto.validation;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ober6.probes.dto.DeviationData;

public class DeviationDataValidationTest {

  private static Validator validator;

  @BeforeAll
  static void setupValidator() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void invalidId_ShouldFailValidation() {
    DeviationData invalid = new DeviationData(0, 1.0, 2.0, System.currentTimeMillis());

    Set<ConstraintViolation<DeviationData>> violations = validator.validate(invalid);

    assertThat(violations)
        .anyMatch(
            v ->
                v.getPropertyPath().toString().equals("id")
                    && v.getMessage().equals("Sensor ID must be >= 1"));
  }
}
