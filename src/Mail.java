import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail extends Table {

    public void SendMail(){
        final String username = "nyazawa99@gmail.com";
        final String password = "niconiconii";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("nyazawa99@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("emadelaydi16@gmail.com"));
            message.setSubject("LOOOOL");
            message.setText("From Sajeev");

            Transport.send(message);

            System.out.println("Receipt Sent");

        } catch (MessagingException e) {
            System.out.println("could not send receipt");
        }
    }

    public static void main(String[] args){
        Mail ma = new Mail();
        
        for(int i=0; i<3; i++)
            { 
                ma.SendMail();
            }
    }
}