package top.ninng.qs.admin.service;

import top.ninng.qs.admin.entity.Config;
import top.ninng.qs.common.entity.UnifyResponse;

import java.util.ArrayList;

/**
 * 管理接口
 *
 * @Author OhmLaw
 * @Date 2023/2/13 16:31
 * @Version 1.0
 */
public interface IAdminService {

    /**
     * 获取所有配置
     *
     * @return 所有配置信息
     */
    UnifyResponse<ArrayList<Config>> getAllConfig();

    /**
     * 设置配置
     *
     * @param id           id
     * @param key          key值
     * @param value        配置数值
     * @param info         配置描述
     * @param defaultValue 配置默认值
     * @return
     */
    UnifyResponse<String> updateConfig(long id, String key, String value, String info, String defaultValue);
}
