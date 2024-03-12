package top.ninng.qs.file.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.EmptyCheck;
import top.ninng.qs.file.entity.FileItem;
import top.ninng.qs.file.service.IFileService;
import top.ninng.qs.file.utils.Path;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件服务实现类
 *
 * @Author OhmLaw
 * @Date 2023/1/9 14:30
 * @Version 1.0
 */
@Service
public class FileServiceImpl implements IFileService {

    String imgPath = Path.getWorkAbsolutePath() + "/data/img/";
    Pattern pattern = Pattern.compile("[^.]*$");
    String defaultName = "问号-1caa408d-3203-4a84-8b28-ea254c94a2be.png";
    String defaultImagePath = imgPath + defaultName;

    /**
     * 获取图片
     *
     * @param name     图片名
     * @param response 响应
     * @return 图片
     */
    @Override
    public BufferedImage getImage(String name, HttpServletResponse response) {
        // 创建文件对象
        File imageFile = new File(imgPath + name);
        if (!imageFile.exists()) {
            // 指定图片不存在时，响应默认图片
            imageFile = new File(defaultImagePath);
            name = defaultName;
        }
        OutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(imageFile);
            BufferedImage image = ImageIO.read(inputStream);

            // 获取输出流
            outputStream = response.getOutputStream();

            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                ImageIO.write(image, matcher.group(), outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (EmptyCheck.notEmpty(outputStream)) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (EmptyCheck.notEmpty(inputStream)) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param files 文件名
     * @return 上传结果
     */
    @Override
    public UnifyResponse<ArrayList<FileItem>> upload(MultipartFile[] files) {
        ArrayList<FileItem> newFileNameList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (EmptyCheck.isEmpty(file) || file.isEmpty()) {
                return UnifyResponse.fail("上传文件为空！", null);
            }
            // 文件后缀
            String suffix =
                    Objects.requireNonNull(file.getOriginalFilename())
                            .substring(file.getOriginalFilename().lastIndexOf("."));
            // 文件名加 UUID 处理
            String name = Objects.requireNonNull(file.getOriginalFilename())
                    .substring(0, file.getOriginalFilename().lastIndexOf("."));
            String newFileName = name + "-" + UUID.randomUUID() + suffix;
            newFileNameList.add(new FileItem("/file/image/" + newFileName, newFileName));
            File fileTemp = new File(imgPath + newFileName);
            try {
                file.transferTo(fileTemp);
            } catch (IOException e) {
                e.printStackTrace();
                return UnifyResponse.fail("上传失败！", null);
            }
        }
        return UnifyResponse.ok("上传成功！", newFileNameList);
    }
}
