package top.ninng.qs.file.service;

import org.springframework.web.multipart.MultipartFile;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.file.entity.FileItem;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * 文件服务接口
 *
 * @Author OhmLaw
 * @Date 2023/1/9 14:29
 * @Version 1.0
 */
public interface IFileService {

    /**
     * 获取 image
     *
     * @param name 图片名
     * @return 图片
     */
    BufferedImage getImage(String name, HttpServletResponse response);

    /**
     * 上传 image
     *
     * @param files 文件名
     * @return 上传结果
     */
    UnifyResponse<ArrayList<FileItem>> upload(MultipartFile[] files);
}
