package org.lb.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_METHOD)
public class RoundRobinLoadBalancerTest {

  LoadBalancerV2 lb;

  @BeforeEach
  public void setup() {
    this.lb = new RoundRobinLoadBalancer();
    this.lb.register("com.test.lb", "127.0.0.1", 9091);
  }

  @Test
  public void testRegister() {
    Assertions.assertEquals(1, this.lb.size());
    Assertions.assertThrows(IllegalStateException.class,
        () -> this.lb.register("com.test.lb", "127.0.0.1", 9092));
    Assertions.assertEquals(1, this.lb.size());
  }

  @Test
  public void testDeRegister() {
    this.lb.deregister(null);
    Assertions.assertEquals(1, this.lb.size());
    this.lb.deregister("com.test.lb");
    Assertions.assertEquals(0, this.lb.size());
    this.lb.deregister("com.test.lb");
    Assertions.assertEquals(0, this.lb.size());
  }

  @Test
  public void testGet() {
    ServerInstance instance = this.lb.get();
    Assertions.assertEquals("com.test.lb", instance.getInstanceName());
    this.lb.register("com.test.lb2", "127.0.0.2", 9092);
    this.lb.register("com.test.lb3", "127.0.0.3", 9093);
    instance = this.lb.get();
    Assertions.assertEquals("com.test.lb2", instance.getInstanceName());
    instance = this.lb.get();
    Assertions.assertEquals("com.test.lb3", instance.getInstanceName());
    instance = this.lb.get();
    Assertions.assertEquals("com.test.lb", instance.getInstanceName());
    this.lb.deregister("com.test.lb2");
    instance = this.lb.get();
    Assertions.assertEquals("com.test.lb3", instance.getInstanceName());
    this.lb.deregister("com.test.lb3");
    instance = this.lb.get();
    Assertions.assertEquals("com.test.lb", instance.getInstanceName());
    this.lb.deregister("com.test.lb");
    instance = this.lb.get();
    Assertions.assertNull(instance);
  }
}
