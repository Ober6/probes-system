package org.ober6.probes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ober6.probes.dto.DeviationData;
import org.ober6.probes.dto.ProbeData;
import org.ober6.probes.dto.Range;
import org.ober6.probes.service.RangeProviderClient;
import org.springframework.cloud.stream.function.StreamBridge;

@ExtendWith(MockitoExtension.class)
class AnalyzerApplUnitTest {

  private static final long SENSOR_ID = 123;
  private static final double MIN_VALUE = 100;
  private static final double MAX_VALUE = 200;
  private static final double NORMAL_VALUE = 150;
  private static final double VALUE_LESS_MIN = 50;
  private static final double VALUE_GREATER_MAX = 220;

  private static final Range RANGE = new Range(MIN_VALUE, MAX_VALUE);
  private static final double DEVIATION_GREATER_MAX = VALUE_GREATER_MAX - MAX_VALUE;
  private static final double DEVIATION_LESS_MIN = VALUE_LESS_MIN - MIN_VALUE;

  @Mock RangeProviderClient service;

  @Mock StreamBridge bridge;

  @InjectMocks AnalyzerAppl analyzerAppl;

  ObjectMapper mapper = new ObjectMapper();

  private ProbeData probeNormalData;
  private ProbeData probeGreaterMaxData;
  private ProbeData probeLessMinData;

  @BeforeEach
  void setUp() {
    when(service.getRange(SENSOR_ID)).thenReturn(RANGE);

    probeNormalData = new ProbeData(SENSOR_ID, NORMAL_VALUE, System.currentTimeMillis());
    probeGreaterMaxData = new ProbeData(SENSOR_ID, VALUE_GREATER_MAX, System.currentTimeMillis());
    probeLessMinData = new ProbeData(SENSOR_ID, VALUE_LESS_MIN, System.currentTimeMillis());

    analyzerAppl.producerBindingName = "testProducer";
  }

  @Test
  void testNoDeviation() {
    Consumer<ProbeData> consumer = analyzerAppl.analyzerConsumer();
    consumer.accept(probeNormalData);

    verify(bridge, never()).send(anyString(), any());
  }

  @Test
  void testGreaterMaxDeviation() {
    Consumer<ProbeData> consumer = analyzerAppl.analyzerConsumer();
    consumer.accept(probeGreaterMaxData);

    ArgumentCaptor<DeviationData> captor = ArgumentCaptor.forClass(DeviationData.class);
    verify(bridge, times(1)).send(eq("testProducer"), captor.capture());

    DeviationData deviation = captor.getValue();
    assertEquals(SENSOR_ID, deviation.id());
    assertEquals(DEVIATION_GREATER_MAX, deviation.deviation());
    assertEquals(VALUE_GREATER_MAX, deviation.value());
  }

  @Test
  void testLessMinDeviation() {
    Consumer<ProbeData> consumer = analyzerAppl.analyzerConsumer();
    consumer.accept(probeLessMinData);

    ArgumentCaptor<DeviationData> captor = ArgumentCaptor.forClass(DeviationData.class);
    verify(bridge, times(1)).send(eq("testProducer"), captor.capture());

    DeviationData deviation = captor.getValue();
    assertEquals(SENSOR_ID, deviation.id());
    assertEquals(DEVIATION_LESS_MIN, deviation.deviation());
    assertEquals(VALUE_LESS_MIN, deviation.value());
  }
}
