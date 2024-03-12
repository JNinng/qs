package top.ninng.qs.admin.utils;

import org.springframework.stereotype.Component;
import top.ninng.qs.admin.entity.Config;
import top.ninng.qs.admin.mapper.ConfigMapper;
import top.ninng.qs.common.utils.EmptyCheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取数据库中的配置信息，并转化为 map
 *
 * @Author OhmLaw
 * @Date 2023/1/10 19:58
 * @Version 1.0
 */
@Component
public class GetConfig {

    ConfigMapper configMapper;
    ArrayList<Config> configArrayList;
    /**
     * 配置信息 map
     */
    Map<String, String> map;

    public GetConfig(ConfigMapper configMapper) {
        this.configMapper = configMapper;
        updateData();
    }

    public ArrayList<Config> getConfigArrayList() {
        return configArrayList;
    }

    public void setConfigArrayList(ArrayList<Config> configArrayList) {
        this.configArrayList = configArrayList;
    }

    /**
     * @return 配置信息 map
     */
    public Map<String, String> map() {
        if (EmptyCheck.isEmpty(map)) {
            updateData();
        }
        return map;
    }

    public void updateData() {
        configArrayList = configMapper.selectAll();
        map = new HashMap<>(configArrayList.size());
        for (Config config : configArrayList) {
            map.put(config.getKey(), config.getValue());
        }
    }
}
