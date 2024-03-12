package top.ninng.qs.gateway.filte;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.ninng.qs.gateway.utils.Ip;

/**
 * @Author OhmLaw
 * @Date 2023/2/16 11:18
 * @Version 1.0
 */
@Component
@Order(0)
public class AuthorizationFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        System.out.println(String.valueOf(exchange.getRequest().getHeaders().get("token")));
        mutate.header("gateway_token", "6ff71f1a-68ec-4f76-b0c3-e39882e574a0");
        mutate.header("user_ip", Ip.getIpAddr(exchange.getRequest()));
        if (StpUtil.isLogin()) {
            mutate.header("user_id", StpUtil.getLoginId().toString());
        }
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
