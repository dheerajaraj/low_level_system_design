package org.lb.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoadBalancerImplTest {

  public LoadBalancerImpl lb;

  @BeforeAll
  public void setup() {
    this.lb = new LoadBalancerImpl();
    this.lb.register("com.test.lb", "127.0.0.1", 9091);
  }

  @AfterAll
  public void tearDown() {
    this.lb.clear();
    this.lb = null;
  }

  @Test
  @Order(1)
  public void testRegister() {
    Assertions.assertEquals(1, lb.size());
    ServerInstance instance = lb.get("com.test.lb");
    Assertions.assertNotNull(instance);
    Assertions.assertNotNull(instance.getIpAddr());
    Assertions.assertEquals("com.test.lb", instance.getInstanceName());
    Assertions.assertEquals("127.0.0.1", instance.getIpAddr().getIpAddr());
    Assertions.assertEquals(9091, instance.getIpAddr().getPortNum());

    lb.register("com.test.lb", "196.0.0.1", 9081);
    Assertions.assertEquals(1, lb.size());
    ServerInstance secondInstance = lb.get("com.test.lb");
    Assertions.assertEquals("127.0.0.1", secondInstance.getIpAddr().getIpAddr());
  }

  @Test
  @Order(2)
  public void testDeRegister() {
    lb.deregister("com.test.lb");
    ServerInstance instance = lb.get("com.test.lb");
    Assertions.assertNull(instance);
    Assertions.assertEquals(0, lb.size());
  }

  @Test
  @Order(3)
  public void stressTestRegistersAndDeRegisters() {
    int initialSize = lb.size();
    Thread thread1 = new Thread(() -> lb.register("com.dheeraj.test1", "195.10.10.1", 9091));
    Thread thread2 = new Thread(() -> lb.register("com.dheeraj.test2", "195.10.10.2", 9092));
    Thread thread3 = new Thread(() -> lb.register("com.dheeraj.test3", "195.10.10.3", 9093));

    Thread deregT1 = new Thread(() -> lb.deregister("com.dheeraj.test1"));
    Thread deregT2 = new Thread(() -> lb.deregister("com.dheeraj.test2"));
    Thread deregT3 = new Thread(() -> lb.deregister("com.dheeraj.test3"));

    try {
      thread1.start();
      thread2.start();
      thread3.start();
      thread1.join();
      deregT1.start();
      thread2.join();
      deregT2.start();
      thread3.join();
      deregT3.start();
      deregT1.join();
      deregT2.join();
      deregT3.join();
      Assertions.assertEquals(initialSize, lb.size());
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }
}
