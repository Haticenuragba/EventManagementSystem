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
}
