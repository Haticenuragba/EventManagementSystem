package yte.intern.project.EventManagementSystem.usecases.sendqrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.dto.ApplicationDTO;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.mapper.ApplicationMapper;
import yte.intern.project.EventManagementSystem.usecases.sendqrcode.objects.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public String sendSimpleMessage(ApplicationDTO applicationDTO) throws MessagingException, IOException, WriterException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("trybruteforcefirst@gmail.com");
        helper.setTo(applicationDTO.getEmail());

        helper.setText("Tebrikler, " + applicationDTO.getEvent().getTitle()
                + " etkinliğine kaydınız başarıyla tamamlandı.\n"
                + "Etkinlik detaylarına ekteki kare koddan ulaşabilirsiniz.", true);
        helper.setSubject(applicationDTO.getEvent().getTitle());

        String qrCodeText = "Etkinlik İsmi: " + applicationDTO.getEvent().getTitle()
                + "\nEtkinlik Açıklaması: " + applicationDTO.getEvent().getDescription()
                + "\nEtkinlik Tarihi: " + applicationDTO.getEvent().getStartDate()
                + " - " + applicationDTO.getEvent().getEndDate()
                + "\nKatılımcı İsmi " + applicationDTO.getName() + " " + applicationDTO.getSurname()
                + "\nKatılımcı T.C Kimlik Numarası: " + applicationDTO.getIdNumber();

        byte[] image = this.getQRCodeImage(qrCodeText, 500, 500);
        helper.addAttachment("qrCode.jpg", new ByteArrayResource(image));
        emailSender.send(message);
        return Base64Utils.encodeToString(image);
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
