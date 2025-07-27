package org.lb.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServerInstanceTest {

  @Test
  public void testValidateInstanceName() {
    Assertions.assertThrows(IllegalStateException.class,
        () -> new ServerInstance("", "256.86.10.10", 9092));
    Assertions.assertThrows(IllegalStateException.class,
        () -> new ServerInstance(null, "256.86.10.10", 9092));
  }
}
