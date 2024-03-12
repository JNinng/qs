package top.ninng.qs.file.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.file.entity.FileItem;
import top.ninng.qs.file.service.IFileService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * 文件控制器
 *
 * @Author OhmLaw
 * @Date 2023/1/8 20:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {

    ServletContext servletContext;
    IFileService iFileService;

    public FileController(ServletContext servletContext, IFileService iFileService) {
        this.servletContext = servletContext;
        this.iFileService = iFileService;
    }

    /**
     * 文件服务，获取图片
     *
     * @param name     图片名
     * @param response 响应
     * @return 指定图片
     */
    @RequestMapping(value = "/image/{name}", method = RequestMethod.GET)
    public BufferedImage getImage(
            @PathVariable(value = "name") String name,
            HttpServletResponse response) {
        return iFileService.getImage(name, response);
    }

    /**
     * 上传文件
     *
     * @param files 上传目标文件
     * @return 上传结果
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public UnifyResponse<ArrayList<FileItem>> upload(
            @RequestParam(value = "files") MultipartFile[] files) {
        return iFileService.upload(files);
    }
}
