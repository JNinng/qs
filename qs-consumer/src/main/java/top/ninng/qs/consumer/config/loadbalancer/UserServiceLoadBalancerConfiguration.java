package top.ninng.qs.consumer.config.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * userService服务负载均衡配置
 *
 * @author OhmLaw
 */
public class UserServiceLoadBalancerConfiguration {

    @Bean
    ReactorLoadBalancer<ServiceInstance> getLoadBalancer(Environment environment,
                                                         LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        //        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name,
        //                ServiceInstanceListSupplier.class), name);
        //        return new MyLoadBalancer(loadBalancerClientFactory.getLazyProvider(name,
        //                ServiceInstanceListSupplier.class));
        return new NacosWeightLoadBalancer(name, loadBalancerClientFactory.getLazyProvider(name,
                ServiceInstanceListSupplier.class));
    }
    //    @Bean
    //    public ReactorServiceInstanceLoadBalancer reactorServiceInstanceLoadBalancer(Environment environment,
    //    LoadBalancerClientFactory loadBalancerClientFactory) {
    //        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
    //        System.out.println("My");
    //        //返回随机轮询负载均衡方式
    //        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name,
    //        ServiceInstanceListSupplier.class), name);
    //    }
}
