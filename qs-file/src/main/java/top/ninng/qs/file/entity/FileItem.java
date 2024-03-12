package top.ninng.qs.file.entity;

import java.io.Serializable;

/**
 * 文件上传结果
 *
 * @Author OhmLaw
 * @Date 2023/2/13 20:26
 * @Version 1.0
 */
public class FileItem implements Serializable {

    private static final long serialVersionUID = 671517744637486683L;

    String url;
    String title;

    public FileItem() {
    }

    public FileItem(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
