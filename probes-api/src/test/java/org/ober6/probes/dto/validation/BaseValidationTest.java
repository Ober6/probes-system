package org.ober6.probes.dto.validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/** Base class for providing a shared Validator instance to all validation test classes. */
public abstract class BaseValidationTest {

  protected static Validator validator;

  static {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }
}
