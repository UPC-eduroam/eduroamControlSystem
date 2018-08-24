package cn.edu.upc.eduroamcontrolsystembackend.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 发送邮件工具类
 * Created by jay on 2018/08/22
 */

@Service
public class EmailUtil {

    private static String HOST;
    private static String PROTOCOL;
    private static int PORT;
    private static String EMAILADDRESS;
    private static String PASSWORD;

    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.store.protocol", PROTOCOL);//设置协议
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", String.valueOf(PORT));

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAILADDRESS, PASSWORD);
            }
        };
        return Session.getDefaultInstance(props, authenticator);
    }

    public static boolean send(String toEmail, String content) {
        Session session = getSession();
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(EMAILADDRESS));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("[来自eduroam安全管控系统的邮件]");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=utf-8");
            msg.saveChanges();
            Transport.send(msg);
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return false;
    }

    @Value("${email.host}")
    public void setHOST(String HOST) {
        EmailUtil.HOST = HOST;
    }

    @Value("${email.protocol}")
    public void setPROTOCOL(String PROTOCOL) {
        EmailUtil.PROTOCOL = PROTOCOL;
    }

    @Value("${email.port}")
    public void setPORT(int PORT) {
        EmailUtil.PORT = PORT;
    }

    @Value("${email.username}")
    public void setEMAILADDRESS(String EMAILADDRESS) {
        EmailUtil.EMAILADDRESS = EMAILADDRESS;
    }

    @Value("${email.password}")
    public void setPASSWORD(String PASSWORD) {
        EmailUtil.PASSWORD = PASSWORD;
    }
}