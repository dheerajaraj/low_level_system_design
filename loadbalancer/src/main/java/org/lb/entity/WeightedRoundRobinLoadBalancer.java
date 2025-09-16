package org.lb.entity;

/**
 * Algorithm is the same as @see org.lb.entity.RoundRobinLoadBalancer Just that we need to have
 * weights for each serverInstance and counter will be the mod of sum of all the weights
 * <p>
 * Use LinkedList (once 1st instance is done, dequeue and then enqueue) Once number of requests
 * served == weight of instance, dequeue and enqueue
 */
public class WeightedRoundRobinLoadBalancer implements LoadBalancerV2 {

  @Override
  public void register(String instanceName, String ipAddr, int portNum) {

  }

  @Override
  public void deregister(String instanceName) {

  }

  @Override
  public ServerInstance get() {
    return null;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public void clear() {

  }
}
