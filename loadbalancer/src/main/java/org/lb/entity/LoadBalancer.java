package org.lb.entity;

public interface LoadBalancer {

  /**
   * registers the instance with the loadBalancer
   * @param instanceName
   * @param ipAddr
   * @param portNum
   */
  void register(String instanceName, String ipAddr, int portNum);

  /**
   * Removes the instance from the loadbalancer
   * @param instanceName
   */
  void deregister(String instanceName);

  /**
   * Retrieves the ServerInstance from the load balancer
   * @param serverName
   * @return
   */
  ServerInstance get(String serverName);

  /**
   * Gets the number of server instances in the load balancer
   * @return
   */
  int size();

  void clear();

}
