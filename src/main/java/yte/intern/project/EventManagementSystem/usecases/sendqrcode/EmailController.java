package yte.intern.project.EventManagementSystem.usecases.sendqrcode;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yte.intern.project.EventManagementSystem.usecases.sendqrcode.helpers.QrCodeGenerator;
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
        try {
            Mail mail = new Mail("hnagba@gmail.com", "sevdenuragba@gmail.com", "Kayıt Başarılı", "Tebrikler" +
                    " etkinliğe başarıyla kaydoldunuz. Etkinlik bilgilerine ekteki kare koddan ulaşabilirsiniz.");
            QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
            byte[] image = qrCodeGenerator.getQRCodeImage("Deneme qr", 250, 250);
            emailService.sendSimpleMessage(mail, image);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return "Sended";
    }
}
