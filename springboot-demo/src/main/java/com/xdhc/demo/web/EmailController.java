package com.xdhc.demo.web;

import com.xdhc.demo.entity.Blog;
import com.xdhc.demo.entity.Result;
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
}