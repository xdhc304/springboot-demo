package com.xdhc.demo.web;

import com.xdhc.demo.entity.Result;
import com.xdhc.demo.service.impl.MailServiceImpl;
import com.xdhc.demo.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Api(value = "EmailController")
@RestController
@RequestMapping("/email")
@Component("emailtool")
public class EmailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailServiceImpl mailService;//注入发送邮件的各种实现方法

    @Autowired
    private TemplateEngine templateEngine;//注入模板引擎

    private static final Logger logger  =  LoggerFactory.getLogger(EmailController.class);

    /**
     * 发送email
     *
     * @return
     */
    @ApiOperation(value = "sendEmail")
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    private Result<Map<String, Object>> sendEmail() {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("1*156*156*6@163.com");
            helper.setTo("857446812@qq.com");
            helper.setSubject("标题：电子发票");

            StringBuffer sb = new StringBuffer();
            sb.append("<h1>电子发票</h1>")
                    .append("<p style='color:#F00'>已到</p>")
                    .append("<p style='text-align:right'>spring boot发送</p>");
            helper.setText(sb.toString(), true);
//            FileSystemResource fileSystemResource = new FileSystemResource(new File("D:\76678.pdf"));
//            helper.addAttachment("电子发票", fileSystemResource);
            javaMailSender.send(message);
            return ResultUtil.success("邮件发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResultUtil.error("邮件发送失败");
        }
    }

    @RequestMapping(value = "/simpleEmail", method = RequestMethod.GET)
    private Result<Map<String, Object>> simpleEmail() {
        try {
            mailService.sendSimpleMail("857446812@qq.com","SpringBoot Email","这是一封普通的SpringBoot测试邮件");
            return ResultUtil.success("邮件发送成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResultUtil.error("邮件发送失败");
        }
    }

    @RequestMapping(value = "/htmlEmail", method = RequestMethod.GET)
    public Result<Map<String, Object>> htmlEmail(){
        try {
            mailService.sendHtmlMail("1605668795@qq.com","IJPay让支付触手可及","<body style=\"text-align: center;margin-left: auto;margin-right: auto;\">\n"
                + "	<div id=\"welcome\" style=\"text-align: center;position: absolute;\" >\n"
                + "		<h3>欢迎使用IJPay -By Javen</h3>\n"
                + "		<span>https://github.com/Javen205/IJPay</span>"
                + "		<div\n"
                + "			style=\"text-align: center; padding: 10px\"><a style=\"text-decoration: none;\" href=\"https://github.com/Javen205/IJPay\" target=\"_bank\" ><strong>IJPay 让支付触手可及,欢迎Start支持项目发展:)</strong></a></div>\n"
                + "		<div\n" + "			style=\"text-align: center; padding: 4px\">如果对你有帮助,请任意打赏</div>\n"
                + "		<img width=\"180px\" height=\"180px\"\n"
                + "			src=\"https://javen205.gitbooks.io/ijpay/content/assets/wxpay.png\">\n"
                + "	</div>\n" + "</body>");
            return ResultUtil.success("邮件发送成功");
        } catch (Exception ex){
            ex.printStackTrace();
            return ResultUtil.error("邮件发送失败");
        }
    }

    @RequestMapping(value = "/attachmentsMail", method = RequestMethod.GET)
    public Result<Map<String, Object>> attachmentsMail(){
        try {
            String filePath = "/Users/Javen/Desktop/IJPay.png";
            mailService.sendAttachmentsMail("857446812@qq.com", "这是一封带附件的邮件", "邮件中有附件，请注意查收！", filePath);
            return ResultUtil.success("邮件发送成功");
        } catch (Exception ex){
            ex.printStackTrace();
            return ResultUtil.error("邮件发送失败");
        }
    }

    @RequestMapping(value = "/resourceMail", method = RequestMethod.GET)
    public Result<Map<String, Object>> resourceMail(){
        try {
            String rscId = "IJPay";
            String content = "<html><body>这是有图片的邮件<br/><img src=\'cid:" + rscId + "\' ></body></html>";
            String imgPath = "/Users/Javen/Desktop/IJPay.png";
            mailService.sendResourceMail("857446812@qq.com", "这邮件中含有图片", content, imgPath, rscId);
            return ResultUtil.success("邮件发送成功");
        } catch (Exception ex){
            ex.printStackTrace();
            return ResultUtil.error("邮件发送失败");
        }
    }

    @RequestMapping(value = "/templateMail", method = RequestMethod.GET)
    public Result<Map<String, Object>> templateMail(){
        try {
            Context context = new Context();
            context.setVariable("project", "IJPay");
            context.setVariable("author", "Javen");
            context.setVariable("url", "https://github.com/Javen205/IJPay");
            String emailContent = templateEngine.process("emailTemp", context);
            mailService.sendHtmlMail("857446812@qq.com", "这是模板邮件", emailContent);
            return ResultUtil.success("邮件发送成功");
        } catch (Exception ex){
            ex.printStackTrace();
            return ResultUtil.error("邮件发送失败");
        }
    }

}