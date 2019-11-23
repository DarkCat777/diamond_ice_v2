package controller.mail;

import java.util.Properties;

public class PropertiesMailFactory {
    public static final String GMAIL = "GMAIL";
    public Properties getMailProperties(String serverMail){
        switch (serverMail){
            case PropertiesMailFactory.GMAIL:
                return new GmailProperties().getProperties();
            default:
                return null;
        }
    }
}
