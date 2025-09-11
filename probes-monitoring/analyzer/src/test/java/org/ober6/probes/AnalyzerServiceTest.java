package org.ober6.probes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.ober6.probes.dto.Range;
import org.ober6.probes.service.RangeProviderClient;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnalyzerServiceTest {

  private static final long SENSOR_ID = 123;
  private static final Range RANGE = new Range(100, 200);
  private static final long SENSOR_ID_NOT_FOUND = 124;
  private static final Range RANGE_DEFAULT =
      new Range(RangeProviderClient.MIN_DEFAULT_VALUE, RangeProviderClient.MAX_DEFAULT_VALUE);

  // Минимальный stub, чтобы тесты проходили
  RangeProviderClient service =
      new RangeProviderClient() {
        @Override
        public Range getRange(long sensorId) {
          if (sensorId == SENSOR_ID) return RANGE;
          return RANGE_DEFAULT;
        }
      };

  @Test
  @Order(1)
  void testNormalFlowNoCache() {
    assertEquals(RANGE, service.getRange(SENSOR_ID));
  }

  @Test
  @Order(2)
  void testSensorNotFound() {
    assertEquals(RANGE_DEFAULT, service.getRange(SENSOR_ID_NOT_FOUND));
  }
}
