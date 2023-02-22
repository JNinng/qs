package top.ninng.qs.gateway.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author OhmLaw
 * @Date 2023/2/16 14:15
 * @Version 1.0
 */
@Configuration
public class SaTokenConfig {

    @Bean
    public SaReactorFilter getReactorFilter() {
        return new SaReactorFilter()
                .addInclude("/admin/**", "/comment/comment", "/article/getPageList", "/article/pageInfo",
                        "/article/updateById", "/article/updateInfo", "/article/upload", "/tag/deleteTagByName",
                        "/tag/deleteById", "/tag/updateById", "/user/checkLogin")
                .setAuth(o -> {
                    SaRouter.match("/**", "/user/login", r -> StpUtil.checkLogin());
                    SaRouter.match("/admin/**", r -> StpUtil.checkRole("admin"));
                })
                .setError(e -> {
                    ServerWebExchange exchange = SaReactorSyncHolder.getContext();
                    exchange.getResponse().getHeaders().set("Content-Type", "application/json; charset=utf-8");
                    return SaResult.error(e.getMessage());
                })
                .setBeforeAuth(o -> {
                    SaHolder.getResponse()
                            // 允许指定域访问跨域资源
                            .setHeader("Access-Control-Allow-Origin", "*")
                            // 允许所有请求方式
                            .setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                            // 有效时间
                            .setHeader("Access-Control-Max-Age", "3600")
                            // 允许的header参数
                            .setHeader("Access-Control-Allow-Headers", "*");
                    //                    如果是预检请求，则立即返回到前端
                    SaRouter.match(SaHttpMethod.OPTIONS)
                            .free(r -> System.out.println("--------OPTIONS预检请求，不做处理"))
                            .back();
                });
    }
}
