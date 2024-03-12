package top.ninng.qs.gateway.utils;

import org.springframework.http.server.reactive.ServerHttpRequest;
import top.ninng.qs.common.utils.EmptyCheck;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * ip 处理
 *
 * @Author OhmLaw
 * @Date 2023/1/8 14:13
 * @Version 1.0
 */
public class Ip {

    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST = "127.0.0.1";
    private static final String SEPARATOR = ",";

    private static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
    private static final String HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    /**
     * @param request 请求
     * @return IP 地址
     */
    public static String getIpAddr(ServerHttpRequest request) {
        String ipAddress;
        try {
            // 1.根据常见的代理服务器转发的请求ip存放协议，从请求头获取原始请求ip。值类似于203.98.182.163, 203.98.182.163
            ipAddress = request.getHeaders().getFirst(HEADER_X_FORWARDED_FOR);
            if (EmptyCheck.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeaders().getFirst(HEADER_PROXY_CLIENT_IP);
            }
            if (EmptyCheck.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeaders().getFirst(HEADER_WL_PROXY_CLIENT_IP);
            }


            // 2.如果没有转发的ip，则取当前通信的请求端的ip
            if (EmptyCheck.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                InetSocketAddress inetSocketAddress = request.getRemoteAddress();
                if (inetSocketAddress != null) {
                    ipAddress = inetSocketAddress.getAddress().getHostAddress();
                }
                // 如果是127.0.0.1，则取本地真实ip
                if (LOCALHOST.equals(ipAddress)) {
                    InetAddress localAddress = null;

                    try {
                        localAddress = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = localAddress.getHostAddress();

                    if (localAddress.getHostAddress() != null) {
                        ipAddress = localAddress.getHostAddress();
                    }
                }
            }


            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***"
            if (ipAddress != null) {
                ipAddress = ipAddress.split(SEPARATOR)[0].trim();
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress == null ? "" : ipAddress;
    }
}
