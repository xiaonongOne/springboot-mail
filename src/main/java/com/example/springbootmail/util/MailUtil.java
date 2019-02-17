package com.example.springbootmail.util;

import com.example.springbootmail.bean.MailBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author xz
 * @function 邮件发送工具类
 * @date 2019-01-25 20:14
 */
@Component
public class MailUtil {
    @Value("${spring.mail.username}")
    private String MAIL_SENDER; //邮件发送者

    @Autowired
    private JavaMailSender javaMailSender;

    private Logger logger = LoggerFactory.getLogger(MailUtil.class);

    /**
     * 发送一个简单格式的邮件
     *
     * @param mailBean
     */
    public  void sendSimpleMail(MailBean mailBean) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            //邮件发送人
            mailMessage.setFrom(MAIL_SENDER);
            //邮件接收人
            mailMessage.setTo(mailBean.getRecipient());
            //邮件主题
            mailMessage.setSubject(mailBean.getSubject());
            //邮件内容
            mailMessage.setText(mailBean.getContent());
            //文本邮件抄送使用：copyTo 抄送人
            //simpleMailMessage.copyTo(copyTo);

            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

    /**
     * 发送一个HTML格式(富文本邮件)的邮件
     *
     * @param mailBean
     */
    public void sendHTMLMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            //true 表示需要创建一个multipart message
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMailMessage, true);
            messageHelper.setFrom(MAIL_SENDER);
            messageHelper.setTo(mailBean.getRecipient());
            messageHelper.setSubject(mailBean.getSubject());
            //抄送
            //mimeMessageHelper.addCc("抄送");
            messageHelper.setText(mailBean.getContent(), true);
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

    /**
     * 发送带附件格式的邮件
     *
     * @param mailBean
     */
    public void sendAttachmentMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            //true 表示需要创建一个multipart message
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMailMessage, true);
            messageHelper.setFrom(MAIL_SENDER);
            messageHelper.setTo(mailBean.getRecipient());
            messageHelper.setSubject(mailBean.getSubject());
            messageHelper.setText(mailBean.getContent());
            //文件路径 目前写死在代码中，之后可以当参数传过来，或者在MailBean中添加属性absolutePath
            String absolutePath = "E:\\公众号\\微信二维码图片\\20181226135757396.png";
            FileSystemResource file = new FileSystemResource(new File(absolutePath));
            //FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/email.png"));
            String fileName = absolutePath.substring(absolutePath.lastIndexOf(File.separator));
            //添加附件,第一个参数表示添加到 Email 中附件的名称，第二个参数是图片资源
            messageHelper.addAttachment(fileName, file);
            //多个附件
            //mimeMessageHelper.addAttachment(fileName1, file1);
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

    /**
     * 发送带静态资源的邮件
     *
     * @param mailBean
     */
    public void sendInlineMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mssageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mssageHelper.setFrom(MAIL_SENDER);
            mssageHelper.setTo(mailBean.getRecipient());
            mssageHelper.setSubject(mailBean.getSubject());
            mssageHelper.setText(mailBean.getContent(), true);
            //文件路径
            String absolutePath = "E:\\公众号\\微信二维码图片\\20181226135757396.png";
            FileSystemResource file = new FileSystemResource(new File(absolutePath));
            //FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/email.png"))
            //添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 mimeMessageHelper.addInline(rscId, res) 来实现
            mssageHelper.addInline("picture", file);
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }
}
