package org.lb.entity.valueObj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IPAddrTest {

  @Test
  public void testValidateIpAddress() {
    Assertions.assertThrows(IllegalStateException.class,
        () -> new IPAddr("256.86.10.10", 9092));
    Assertions.assertThrows(IllegalStateException.class,
        () -> new IPAddr("-1.86.10.10", 9092));
    Assertions.assertThrows(IllegalStateException.class,
        () -> new IPAddr("196.86.10.10.10", 9092));
    Assertions.assertThrows(IllegalStateException.class,
        () -> new IPAddr(",256.86.10.10", 9092));
  }
}
