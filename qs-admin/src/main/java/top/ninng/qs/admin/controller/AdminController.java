package top.ninng.qs.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ninng.qs.admin.entity.Config;
import top.ninng.qs.admin.service.IAdminService;
import top.ninng.qs.common.entity.UnifyResponse;

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
        return iAdminService.getAllConfig();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public UnifyResponse<String> updateConfigs(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "key") String key,
            @RequestParam(value = "value") String value,
            @RequestParam(value = "info") String info,
            @RequestParam(value = "defaultValue") String defaultValue) {
        return iAdminService.updateConfig(id, key, value, info, defaultValue);
    }


}
