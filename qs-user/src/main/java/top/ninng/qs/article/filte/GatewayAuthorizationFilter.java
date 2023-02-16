package top.ninng.qs.article.filte;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.ninng.utils.EmptyCheck;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author OhmLaw
 * @Date 2023/2/16 11:25
 * @Version 1.0
 */
@Slf4j
@Component
@WebFilter(filterName = "gatewayFilter", urlPatterns = {"/user/**"})
public class GatewayAuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String gatewayToken = request.getHeader("gateway_token");
        if (EmptyCheck.isEmpty(gatewayToken) && !"6ff71f1a-68ec-4f76-b0c3-e39882e574a0".equals(gatewayToken)) {
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
