package yte.intern.project.EventManagementSystem.usecases.sendqrcode;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yte.intern.project.EventManagementSystem.usecases.sendqrcode.objects.Mail;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/mail")
public class EmailController {

    private EmailService emailService;


    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public String sendMail(){
      return "";
    }
}
