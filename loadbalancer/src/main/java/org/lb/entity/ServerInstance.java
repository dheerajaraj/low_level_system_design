package org.lb.entity;

import java.util.UUID;
import org.lb.entity.valueObj.IPAddr;

public class ServerInstance {

  private UUID id;
  private String instanceName;
  private IPAddr ipAddr;

  public ServerInstance(String instanceName, String ipAddr, int portNum)
      throws IllegalStateException {
    if (instanceName == null || instanceName.isEmpty()) {
      throw new IllegalStateException("Instance name cannot be empty");
    }
    this.id = UUID.randomUUID();
    this.instanceName = instanceName;
    this.ipAddr = new IPAddr(ipAddr, portNum);
  }

  public UUID getId() {
    return id;
  }

  public String getInstanceName() {
    return this.instanceName;
  }

  public IPAddr getIpAddr() {
    return this.ipAddr;
  }
}
