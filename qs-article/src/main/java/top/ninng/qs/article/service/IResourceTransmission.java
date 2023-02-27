package top.ninng.qs.article.service;

import top.ninng.qs.common.entity.UnifyResponse;

import java.util.ArrayList;
import java.util.Date;

/**
 * 资源传输服务接口
 *
 * @Author OhmLaw
 * @Date 2023/2/18 14:43
 * @Version 1.0
 */
public interface IResourceTransmission {

    /**
     * 资源推送
     *
     * @param authorizationCode 授权码
     * @param link              目标服务器
     * @param resourceId        资源 id
     * @return 传输结果
     */
    UnifyResponse<String> push(String authorizationCode,
                               String link,
                               String resourceId);

    /**
     * 接收资源
     *
     * @param authorizationCode 授权码
     * @param mode              模式（0：文章，1：文件）
     * @param title             标题
     * @param content           正文
     * @param tag               标签
     * @param date              时间
     * @return 传输结果
     */
    UnifyResponse<String> receive(
            String authorizationCode,
            int mode,
            String title,
            String content,
            ArrayList<String> tag,
            Date date,
            String userId);
}
