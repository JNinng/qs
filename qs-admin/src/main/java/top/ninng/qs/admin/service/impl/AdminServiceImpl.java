package top.ninng.qs.admin.service.impl;

import org.springframework.stereotype.Service;
import top.ninng.qs.admin.entity.Config;
import top.ninng.qs.admin.mapper.ConfigMapper;
import top.ninng.qs.admin.service.IAdminService;
import top.ninng.qs.admin.utils.GetConfig;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.IdObfuscator;

import java.util.ArrayList;

/**
 * 管理业务
 *
 * @Author OhmLaw
 * @Date 2023/2/13 16:55
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements IAdminService {

    GetConfig getConfig;
    IdObfuscator idObfuscator;
    ConfigMapper configMapper;

    public AdminServiceImpl(GetConfig getConfig, IdObfuscator idObfuscator, ConfigMapper configMapper) {
        this.getConfig = getConfig;
        this.idObfuscator = idObfuscator;
        this.configMapper = configMapper;
    }

    @Override
    public UnifyResponse<ArrayList<Config>> getAllConfig() {
        return UnifyResponse.ok(getConfig.getConfigArrayList());
    }

    @Override
    public UnifyResponse<String> updateConfig(long id, String key, String value, String info, String defaultValue) {
        int result = configMapper.updateByPrimaryKeySelective(new Config(Math.toIntExact(id), key, value, info,
                defaultValue));
        getConfig.updateData();
        return UnifyResponse.ok(result > 0 ? "更新成功！" : "更新失败！", null);
    }
}
