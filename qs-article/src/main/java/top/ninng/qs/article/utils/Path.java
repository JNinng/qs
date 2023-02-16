package top.ninng.qs.article.utils;

/**
 * 路径处理
 *
 * @Author OhmLaw
 * @Date 2023/1/8 18:30
 * @Version 1.0
 */
public class Path {

    /**
     * 获取系统工作绝对路径
     *
     * @return 系统工作绝对路径
     */
    public static String getWorkAbsolutePath() {
        return System.getProperty("user.dir")
                .replaceAll("\\\\[^\\\\]*\\\\[^\\\\]*\\\\$", "")
                .replaceAll("\\\\", "/");
    }
}
