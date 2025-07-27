package org.lb.entity;

import java.util.concurrent.ConcurrentHashMap;

public class LoadBalancerImpl implements LoadBalancer {

  private final ConcurrentHashMap<String, ServerInstance> map;

  public LoadBalancerImpl() {
    this.map = new ConcurrentHashMap<>();
  }

  public void register(String instanceName, String ipAddr, int portNum) {
    try {
      synchronized (this.map) {
        ServerInstance instance = new ServerInstance(instanceName, ipAddr, portNum);
        this.map.computeIfAbsent(instanceName, (key -> instance));
      }
    } catch (IllegalStateException ex) {
      System.out.println("ERROR: Couldn't add instance. " + ex.getMessage());
    }
  }

  public void deregister(String instanceName) {
    synchronized (this.map) {
      this.map.remove(instanceName);
    }
  }

  public ServerInstance get(String serverName) {
    return this.map.getOrDefault(serverName, null);
  }

  public int size() {
    return this.map.size();
  }

  @Override
  public void clear() {
    this.map.clear();
  }
}
