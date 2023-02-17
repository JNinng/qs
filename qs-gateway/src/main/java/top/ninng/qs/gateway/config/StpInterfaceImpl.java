package top.ninng.qs.gateway.config;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author OhmLaw
 * @Date 2023/2/16 14:37
 * @Version 1.0
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String s) {
        List<String> list = new ArrayList<>();
        list.add("login");
        // 返回权限列表
        if (Long.parseLong(String.valueOf(loginId)) == 1) {
            list.add("admin");
        }
        return list;
    }

    @Override
    public List<String> getRoleList(Object loginId, String s) {
        List<String> list = new ArrayList<>();
        list.add("login");
        // 返回 login 拥有的角色列表
        if (Long.parseLong(String.valueOf(loginId)) == 1) {
            list.add("admin");
        }
        return list;
    }
}
