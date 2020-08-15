package yte.intern.project.EventManagementSystem.usecases.manageapplications;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.project.EventManagementSystem.common.exceptionhandling.CustomException;
import yte.intern.project.EventManagementSystem.common.mapper.CycleAvoidingMappingContext;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.dto.ApplicationDTO;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.Application;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.mapper.ApplicationCustomAttributeMapper;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.mapper.ApplicationMapper;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.EventDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;
import yte.intern.project.EventManagementSystem.usecases.sendqrcode.EmailService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("events-application")
public class ManageApplicationController {

    private final ApplicationCustomAttributeMapper applicationCustomAttributeMapper;
    private final ApplicationMapper applicationMapper;
    private final ManageApplicationService manageApplicationService;
    private final EmailService emailService;

    @PostMapping("/{title}")
    public String addApplication(@PathVariable String title, @Valid @RequestBody ApplicationDTO applicationDTO) {
        Application application = applicationMapper.mapToEntity(applicationDTO, new CycleAvoidingMappingContext());

        Application addedApplication = manageApplicationService.addApplication(application, title);
        ApplicationDTO addedApplicationDTO = applicationMapper.mapToDto(addedApplication, new CycleAvoidingMappingContext());
        try {
            return emailService.sendSimpleMessage(addedApplicationDTO);
        } catch (MessagingException | WriterException | IOException e) {
            throw new CustomException("Etkinlik kaydı başarılı, fakat mail gönderilemedi.");
        }

    }



    @GetMapping("/{idNumber}")
    public List<ApplicationDTO> getApplicationsByIdNumber(@PathVariable String idNumber) {
        return applicationMapper.mapToDto(manageApplicationService.getApplicationsByIdNumber(idNumber), new CycleAvoidingMappingContext());
    }
}
