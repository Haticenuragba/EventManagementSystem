package yte.intern.project.EventManagementSystem.usecases.manageapplications;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.project.EventManagementSystem.common.mapper.CycleAvoidingMappingContext;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.dto.ApplicationDTO;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.Application;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.mapper.ApplicationCustomAttributeMapper;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.mapper.ApplicationMapper;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.EventDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("events-application")
public class ManageApplicationController {

    private final ApplicationCustomAttributeMapper applicationCustomAttributeMapper;
    private final ApplicationMapper applicationMapper;
    private final ManageApplicationService manageApplicationService;

    @PostMapping("/{title}")
    public ApplicationDTO addApplication(@PathVariable String title, @Valid @RequestBody ApplicationDTO applicationDTO) {
        Application application = applicationMapper.mapToEntity(applicationDTO, new CycleAvoidingMappingContext());
        Application addedApplication = manageApplicationService.addApplication(application, title);
        return applicationMapper.mapToDto(addedApplication, new CycleAvoidingMappingContext());
    }

    @GetMapping("/{title}")
    public List<ApplicationDTO> getApplicationsOfEvent(@PathVariable String title){
        return applicationMapper.mapToDto(manageApplicationService.getAllApplicationsOfEvent(title), new CycleAvoidingMappingContext());
    }

    @GetMapping
    public List<ApplicationDTO> getAllApplications(){
        return applicationMapper.mapToDto(manageApplicationService.getAllApplications(), new CycleAvoidingMappingContext());
    }

    @GetMapping("/{title}/{idNumber}")
    public ApplicationDTO getApplicationOfEventByIdNumber(@PathVariable String title, @PathVariable String idNumber){
        return applicationMapper.mapToDto(manageApplicationService.getApplicationOfEvent(title, idNumber), new CycleAvoidingMappingContext());
    }
    @GetMapping("/applications/{idNumber}")
    public List<ApplicationDTO> getApplicationsByIdNumber(@PathVariable String idNumber){
        return applicationMapper.mapToDto(manageApplicationService.getApplicationsByIdNumber(idNumber), new CycleAvoidingMappingContext());
    }
}
