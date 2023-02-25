package hyrise.sa.servers.balancer;

import hyrise.sa.servers.balancer.elements.LoadBalancerObject;

public interface LoadBalancer<T extends LoadBalancerObject> {
  T next();
}
