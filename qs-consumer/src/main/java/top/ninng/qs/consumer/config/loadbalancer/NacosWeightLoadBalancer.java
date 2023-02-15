package top.ninng.qs.consumer.config.loadbalancer;

import com.alibaba.nacos.client.naming.utils.Chooser;
import com.alibaba.nacos.client.naming.utils.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 根据Nacos权重负载均衡
 *
 * @Author OhmLaw
 * @Date 2022/9/17 1:24
 * @Version 1.0
 */
public class NacosWeightLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private static final Log log = LogFactory.getLog(NacosWeightLoadBalancer.class);

    private final String serviceId;
    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public NacosWeightLoadBalancer(String serviceId,
                                   ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
        this.serviceId = serviceId;
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
                .getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next().map(this::getInstanceResponse);
    }

    private ServiceInstance getHostByRandomWeight(List<ServiceInstance> serviceInstances) {
        Chooser<String, ServiceInstance> instanceChooser = new Chooser<>("top.ninng");

        List<Pair<ServiceInstance>> hostsWithWeight = serviceInstances.stream()
                .map(serviceInstance -> new Pair<>(serviceInstance, getWeight(serviceInstance)))
                .collect(Collectors.toList());

        instanceChooser.refresh(hostsWithWeight);
        return instanceChooser.randomWithWeight();
    }

    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> serviceInstances) {
        if (serviceInstances.isEmpty()) {
            if (log.isWarnEnabled()) {
                log.warn("No servers available for service: " + serviceId);
            }
            return new EmptyResponse();
        }

        ServiceInstance instance = getHostByRandomWeight(serviceInstances);

        return new DefaultResponse(instance);
    }

    private double getWeight(ServiceInstance serviceInstance) {
        return Double.parseDouble(serviceInstance.getMetadata().get("nacos.weight"));
    }
}
