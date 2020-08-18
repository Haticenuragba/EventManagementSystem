package yte.intern.project.EventManagementSystem.usecases.managemails;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.dto.ApplicationDTO;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.Application;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    private final TaskExecutor taskExecutor;

    public String sendMailWithQrCode(ApplicationDTO applicationDTO) throws MessagingException, IOException, WriterException {

        String qrCodeText = "Etkinlik İsmi: " + applicationDTO.getEvent().getTitle()
                + "\nEtkinlik Açıklaması: " + applicationDTO.getEvent().getDescription()
                + "\nEtkinlik Tarihi: " + applicationDTO.getEvent().getStartDate()
                + " - " + applicationDTO.getEvent().getEndDate()
                + "\nKatılımcı İsmi " + applicationDTO.getName() + " " + applicationDTO.getSurname()
                + "\nKatılımcı T.C Kimlik Numarası: " + applicationDTO.getIdNumber();

        byte[] image = this.getQRCodeImage(qrCodeText, 500, 500);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("trybruteforcefirst@gmail.com");
        helper.setTo(applicationDTO.getEmail());

        helper.setText("Tebrikler, " + applicationDTO.getEvent().getTitle()
                + " etkinliğine kaydınız başarıyla tamamlandı.<br/>"
                + "Etkinlik sırasında aşağıdaki linkten etkinlik sorumlusuna sorularınızı sorabilirsiniz<br/>"
                + "http://localhost:3000/ask-question/" + applicationDTO.getEvent().getQuestionUrl() + "<br/>"
                + "Etkinlik detaylarına ekteki kare koddan ulaşabilirsiniz.", true);
        helper.setSubject(applicationDTO.getEvent().getTitle());


        helper.addAttachment("qrCode.jpg", new ByteArrayResource(image));

        taskExecutor.execute( new Runnable() {
            public void run() {
                try {
                    emailSender.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        return Base64Utils.encodeToString(image);
    }

    public void sendMailWithPassword(String mail, String username, String password) throws MessagingException, IOException, WriterException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("trybruteforcefirst@gmail.com");
        helper.setTo(mail);

        helper.setText("Etkinlik yönetim sistemine etkinlik sorumlusu olarak kaydedildiniz." +
                "\nKullanıcı adınız: " + username +
                "\nŞifreniz: " + password);

        helper.setSubject("Etkinlik Yönetim Sorumlusu Kayıt Bildirimi");


        taskExecutor.execute( new Runnable() {
            public void run() {
                try {
                    emailSender.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void sendMailForEventCancel(Event event) throws MessagingException, IOException, WriterException {
        for(Application a: event.getApplications()){
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("trybruteforcefirst@gmail.com");
            helper.setTo(a.getEmail());

            helper.setText("Daha önce kaydolduğunuz " + event.getTitle() + " isimli etkinlik iptal edilmiştir.");

            helper.setSubject("Etkinlik İptal Bildirimi");


            taskExecutor.execute( new Runnable() {
                public void run() {
                    try {
                        emailSender.send(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }


    }


    private byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }


}
