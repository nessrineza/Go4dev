package esprit.tn.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailingServiceImpl {
    @Autowired
    private  JavaMailSender javaMailSender;

    public void sendSimpleEmail(String toEmail,
                                String body,
                                String Subject){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("oumaima.barika@esprit.tn");
        //message.setFrom("go4dev@outlook.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(Subject);
try {
    javaMailSender.send(message);
}
catch (Exception e)
{
    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    System.out.println(e.getMessage());
}
        System.out.println("Mail Send...");
    }

    public void sendEmailWithAttachment(String toEmail,
                                        String body,
                                        String subject,
                                        String attachment) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("go4dev@outlook.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystem
                = new FileSystemResource(new File(attachment));

        mimeMessageHelper.addAttachment(fileSystem.getFilename(),
                fileSystem);

        javaMailSender.send(mimeMessage);
        System.out.println("Mail Send...");

    }

}
