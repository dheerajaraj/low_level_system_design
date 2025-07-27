package org.lb.entity;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Register, register, register 0(1), get(), register, get() - O(1), get(), deregister(D) - 0(N),
 * get() deregister(A), get() [A, B, C], A (c:0), [A,B,C,D], B(c:1), C(c:2), [A, B, C], increment
 * counter and mod by size
 */


public class RoundRobinLoadBalancer implements LoadBalancerV2 {

  private final ArrayList<ServerInstance> arrayList;
  /**
   * Will be incremented to reflect the next server to serve the request
   */
  private AtomicInteger counter;

  public RoundRobinLoadBalancer() {
    this.arrayList = new ArrayList<>();
    counter = new AtomicInteger(0);
  }


  @Override
  public void register(String instanceName, String ipAddr, int portNum)
      throws IllegalStateException {
    synchronized (this.arrayList) {
      boolean isDuplicate = this.arrayList.stream()
          .anyMatch(server -> server.getInstanceName().equals(instanceName));
      if (isDuplicate) {
        throw new IllegalStateException(
            String.format("Server with servername %s already exists, please de-register",
                instanceName));
      }
      this.arrayList.add(new ServerInstance(instanceName, ipAddr, portNum));
    }
  }

  @Override
  public void deregister(String instanceName) {
    synchronized (this.arrayList) {
      this.arrayList.removeIf(item -> item.getInstanceName().equals(instanceName));
    }
  }

  @Override
  public ServerInstance get() {
    synchronized (this.arrayList) {
      if (this.arrayList.isEmpty()) {
        return null;
      }
      int index = this.counter.updateAndGet(count -> ((count + 1) % this.arrayList.size()));
      return this.arrayList.get(index);
    }
  }

  @Override
  public int size() {
    return this.arrayList.size();
  }

  @Override
  public void clear() {
    this.arrayList.clear();
  }
}
