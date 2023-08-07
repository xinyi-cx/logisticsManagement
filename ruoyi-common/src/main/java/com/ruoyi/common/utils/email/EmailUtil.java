package com.ruoyi.common.utils.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import java.io.File;
import java.util.Properties;

/**
 * @ClassName EmailUtil
 * @Description 邮件发送工具
 * @Author Sophia
 * @Date 2022/4/6 16:06
 */
@Component
public class EmailUtil {

    // 从application.yml配置文件中获取
    // 发送发邮箱地址
    @Value("${spring.mail.from}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送纯文本邮件信息
     *
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容（发送内容）
     */
    public void sendMessage(String[] to, String[] cc, String subject, String content) {
        // 创建一个邮件对象
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        // 设置发送发
        msg.setTo(to);
        // 设置接收方
        if (cc != null && cc.length > 1) {
            msg.setCc(cc);
            //设置抄送
        }
        msg.setSubject(subject);
        // 设置邮件主题
        msg.setText(content);
        // 设置邮件内容

        // 发送邮件
        mailSender.send(msg);
    }

    /**
     * 发送带附件的邮件信息
     *
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容（发送内容）
     * @param files   文件数组 // 可发送多个附件
     */
    public void sendMessageCarryFiles(String[] to, String[] cc, String subject, String content, File[] files) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            // 设置发送发
            helper.setTo(to);
            // 设置接收方
            if (cc != null && cc.length > 1) {
                helper.setCc(cc);
                //设置抄送
            }
            helper.setSubject(subject);
            // 设置邮件主题
            helper.setText(content);
            // 设置邮件内容
            if (files != null && files.length > 0) {
                // 添加附件（多个）
                for (File file : files) {
                    helper.addAttachment(file.getName(), file);
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 发送邮件
        mailSender.send(mimeMessage);
    }

    /**
     * 发送带附件的邮件信息
     *
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容（发送内容）
     * @param file    单个文件
     */
    public void sendMessageCarryFile(String[] to, String[] cc, String subject, String content, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            // 设置发送发
            helper.setTo(to);
            // 设置接收方
            if (cc != null && cc.length > 1) {
                helper.setCc(cc);
                //设置抄送
            }
            helper.setSubject(subject);
            // 设置邮件主题
            helper.setText(content);
            // 设置邮件内容
            helper.addAttachment(file.getName(), file);
            // 单个附件
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 发送邮件
        mailSender.send(mimeMessage);
    }

    public void getFileFromEmail() {
        // Email configuration
        String host = "smtp.163.com"; // Mail server address
        String username = "caixin19920830@163.com"; // Email username
        String password = "THEGROZCGCJAAAYC"; // Email password
        try {
            // Create session
            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol", "imaps"); // Use IMAP protocol
            Session session = Session.getInstance(properties);
            Store store = session.getStore("imaps");
            store.connect(host, username, password);
            // Open inbox folder
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
            // Search for unread messages
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
            Message[] messages = inbox.search(unseenFlagTerm);
            // Iterate through unread messages
            for (Message message : messages) {
                // Check if message has attachments
                if (message.isMimeType("multipart/*")) {
                    Multipart multipart = (Multipart) message.getContent();
                    int numberOfParts = multipart.getCount();
                    for (int partIndex = 0; partIndex < numberOfParts; partIndex++) {
                        MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(partIndex);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            // Save attachment to local disk
                            String fileName = part.getFileName();
                            part.saveFile("/path/to/save/" + fileName);
                            System.out.println("Attachment saved: " + fileName);
                        }
                    }
                }
                // Mark message as read
                message.setFlag(Flags.Flag.SEEN, true);
            }
            // Close connection
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
