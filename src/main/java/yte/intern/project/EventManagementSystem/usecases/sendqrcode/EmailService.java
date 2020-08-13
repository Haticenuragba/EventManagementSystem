package yte.intern.project.EventManagementSystem.usecases.sendqrcode;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import yte.intern.project.EventManagementSystem.usecases.sendqrcode.objects.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public void sendSimpleMessage(Mail mail, byte[] image) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper =  new MimeMessageHelper(message, true);
/*
        String inlineImage = "<img src= 'data:image/jpg;base64, " + Base64Utils.encodeToString(image)
                +"' style='float:right;width:50px;height:50px;'/>";
        System.out.println(inlineImage);*/
        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.addAttachment("qrCode.jpg", new ByteArrayResource(image));
        helper.setText(mail.getContent(), true);
        helper.setSubject(mail.getSubject());

        emailSender.send(message);

    }

}
