package controller.mail;

import java.util.Properties;

public class GmailProperties implements MailProperties {
    @Override
    public Properties getProperties() {
        Properties propiedad = new Properties();
        propiedad.setProperty("controller.mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("controller.mail.smtp.starttls.enable", "true");
        propiedad.setProperty("controller.mail.smtp.port", "587");
        propiedad.setProperty("controller.mail.smtp.auth", "true"); //Error probable
        return propiedad;
    }
}
