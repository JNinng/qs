package top.ninng.qs.common.utils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 邮件工具
 *
 * @Author OhmLaw
 * @Date 2023/1/11 14:52
 * @Version 1.0
 */
public class Email {

    public static MimeMessage createMimeMessage(Session session,
                                                String personal, String email,
                                                String addresseePersonal, String addressee,
                                                String title, String content) {
        // 创建一封邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            // From 发件人
            mimeMessage.setFrom(new InternetAddress(email, personal, "UTF-8"));
            // Recipient 收件人
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(addressee, addresseePersonal,
                    "UTF-8"));
            // TODO 多个收件人
            // Subject 邮件主题
            mimeMessage.setSubject(title, "UTF-8");
            // Content 邮件正文（使用 html 标签）
            mimeMessage.setContent(content, "text/html;charset=UTF-8");
            // TODO 设置发件时间
            // 保存设置
            mimeMessage.saveChanges();

            return mimeMessage;
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param personal          发件人
     * @param email             发件人邮箱
     * @param password          发件邮箱授权密码
     * @param addresseePersonal 收件人
     * @param addressee         收件人邮箱
     * @param title             邮件标题
     * @param content           邮件正文
     * @param protocol          邮件协议（smtp）
     * @param host              邮件服务地址
     * @param port              邮件服务端口
     * @return 是否发送成功
     */
    public static boolean sendEmail(
            String personal, String email, String password,
            String addresseePersonal, String addressee,
            String title, String content,
            String protocol, String host, String port) {
        // 创建连接邮件服务器参数配置
        Properties properties = new Properties();
        // 邮件使用协议（JavaMail规范）
        properties.setProperty("mail.transport.protocol", protocol);
        // 发件人邮箱服务器 SMTP 地址
        properties.setProperty("mail.smtp.host", host);
        // 端口
        properties.setProperty("mail.smtp.port", port);
        // 启用请求认证
        properties.setProperty("mail.smtp.auth", "true");

        // 根据配置创建邮件服务器交互会话对象
        Session session = Session.getInstance(properties);
        // DEBUG 模式的启用
        session.setDebug(true);
        // 创建一封邮件
        MimeMessage mimeMessage = createMimeMessage(session,
                personal, email, addresseePersonal, addressee,
                title, content);

        try {
            // 由 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            // 使用邮箱和授权密码连接邮件服务器
            transport.connect(email, password);
            // 发送邮件
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            // 关闭连接
            transport.close();
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
