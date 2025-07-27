package org.lb.entity;

public interface LoadBalancerV2 {

  /**
   * registers the instance with the loadBalancer
   *
   * @param instanceName
   * @param ipAddr
   * @param portNum
   */
  void register(String instanceName, String ipAddr, int portNum);

  /**
   * Removes the instance from the loadbalancer
   *
   * @param instanceName
   */
  void deregister(String instanceName);

  /**
   * Retrieves the ServerInstance from the load balancer based on a load balancing algorithm
   *
   * @return
   */
  ServerInstance get();

  /**
   * Gets the number of server instances in the load balancer
   *
   * @return
   */
  int size();

  void clear();

}
