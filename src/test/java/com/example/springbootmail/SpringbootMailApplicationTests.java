package com.example.springbootmail;

import com.example.springbootmail.bean.MailBean;
import com.example.springbootmail.util.DateUtils;
import com.example.springbootmail.util.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMailApplicationTests {
    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private TemplateEngine templateEngine;

    //接收人
    private static final String RECIPINET = "18346117690@163.com";

    /**
     * 发送一个简单格式的邮件
     */
   /* @Test
    public void sendSimpleMail() {
        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMail之这是一封简单的邮件");
        mailBean.setContent("SpringBootMail发送一个简单格式的邮件，时间为：" + DateUtils.format(new Date()));

        mailUtil.sendSimpleMail(mailBean);
    }*/

    /**
     * 发送一个HTML格式(富文本邮件)的邮件
     */
   /* @Test
    public void sendHTMLMail() {
        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMailHTML之这是一封HTML格式的邮件");
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>SpirngBoot测试邮件HTML</h2>")
                .append("<p style='text-align:left'>这是一封HTML邮件...</p>")
                .append("<p> 时间为："+ DateUtils.format(new Date()) +"</p>");
        mailBean.setContent(sb.toString());

        mailUtil.sendHTMLMail(mailBean);
    }*/

    /**
     * 发送带附件格式的邮件
     */
    /* @Test
    public void sendAttachmentMail() {
        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMail之这是一封有附件格式的邮件");
        mailBean.setContent("SpringBootMail发送一封有附件格式的邮件，时间为：" + DateUtils.format(new Date()));
        mailUtil.sendAttachmentMail(mailBean);

    }
*/
    /**
     * 发送带静态资源的邮件
     */
   @Test
    public void sendInlineMail() {
        MailBean mailBean = new MailBean();
        //id,目前写死了，可根据需要封装
        String rscId = "picture";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMail之这是一封有静态资源格式的邮件");
        mailBean.setContent(content);

        mailUtil.sendInlineMail(mailBean);
    }

    /**
     * 发送Thymeleaf模版消息
     */
  /*  @Test
    public void sendTemplateMail() {
        //注意：Context 类是在org.thymeleaf.context.Context包下的。
        Context context = new Context();
        //html中填充动态属性值
        context.setVariable("username", "码农用户");
        context.setVariable("url", "https://www.aliyun.com/?utm_content=se_1000301881");
        //注意：process第一个参数名称要和templates下的模板名称一致。要不然会报错
        //org.thymeleaf.exceptions.TemplateInputException: Error resolving template [email]
        String emailContent = templateEngine.process("email", context);

        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("主题：这是模板邮件");
        mailBean.setContent(emailContent);

        mailUtil.sendHTMLMail(mailBean);
    }
    */
}

