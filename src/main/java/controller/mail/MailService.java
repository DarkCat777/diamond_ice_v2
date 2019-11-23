package controller.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {
    private Properties properties;
    private final String correoEnterprise = "diamond.ice.enterprise@gmail.com";
    private final String passwordEnterprise = "983352035edmh";
    private Session session;
    private String receptorMail;
    private String subjectMail;
    private String message;

    public MailService(String serverMail, String correo, String subject, String message) {
        this.properties = new PropertiesMailFactory().getMailProperties(serverMail);
        this.session = Session.getDefaultInstance(this.properties);
        this.receptorMail = correo;
        this.subjectMail = subject;
        this.message = message;
    }

    public boolean sendMessage() {
        MimeMessage mail = new MimeMessage(session);
        try {
            mail.setFrom(new InternetAddress(this.correoEnterprise));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(this.receptorMail));
            mail.setSubject(this.subjectMail);
            mail.setText(this.message);

            Transport transportar = session.getTransport("smtp");
            transportar.connect(this.correoEnterprise, this.passwordEnterprise);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transportar.close();
        } catch (AddressException ex) {
            return false;
        } catch (MessagingException ex) {
            return false;
        }
        return true;
    }

    public String getReceptorMail() {
        return receptorMail;
    }

    public void setReceptorMail(String receptorMail) {
        this.receptorMail = receptorMail;
    }

    public String getSubjectMail() {
        return subjectMail;
    }

    public void setSubjectMail(String subjectMail) {
        this.subjectMail = subjectMail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
