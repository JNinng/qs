package top.ninng.qs.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ninng.entity.UnifyResponse;
import top.ninng.qs.admin.entity.Config;
import top.ninng.qs.admin.service.IAdminService;

import java.util.ArrayList;

/**
 * 管理控制器
 *
 * @Author OhmLaw
 * @Date 2023/2/13 16:30
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    IAdminService iAdminService;

    public AdminController(IAdminService iAdminService) {
        this.iAdminService = iAdminService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public UnifyResponse<ArrayList<Config>> getAllConfigs() {
        if (StpUtil.isLogin() || true) {
            return iAdminService.getAllConfig();
        }
        return UnifyResponse.fail("认证失败！", null);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public UnifyResponse<String> updateConfigs(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "key") String key,
            @RequestParam(value = "value") String value,
            @RequestParam(value = "info") String info,
            @RequestParam(value = "defaultValue") String defaultValue) {
        if (StpUtil.isLogin()) {
            return iAdminService.updateConfig(id, key, value, info, defaultValue);
        }
        return UnifyResponse.fail("认证失败！", null);
    }


}
