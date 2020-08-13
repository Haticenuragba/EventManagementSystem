package yte.intern.project.EventManagementSystem;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import yte.intern.project.EventManagementSystem.usecases.sendqrcode.EmailService;
import yte.intern.project.EventManagementSystem.usecases.sendqrcode.helpers.QrCodeGenerator;
import yte.intern.project.EventManagementSystem.usecases.sendqrcode.objects.Mail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class EventManagementSystemApplication {


	public static void main(String[] args) {
		SpringApplication.run(EventManagementSystemApplication.class, args);
        System.out.println("Try");

    }



}
