package org.ober6.probes.messages;

public interface ErrorMessages {
  String SENSOR_NOT_FOUND = "Sensor not found";
  String WRONG_SENSOR_ID = "Sensor ID must be >= 1";
  String MISSING_RANGE = "Missing range values for sensor";
  String MISSING_EMAILS = "Missing email addresses for sensor";
  String SENSOR_ALREADY_EXISTS = "Sensor is already exists";
}
