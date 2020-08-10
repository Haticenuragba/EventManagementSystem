package yte.intern.project.EventManagementSystem.usecases.manageapplications;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.Application;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.repository.ApplicationCustomAttributeRepository;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.repository.ApplicationRepository;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.CustomAttribute;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;
import yte.intern.project.EventManagementSystem.usecases.manageevents.repository.CustomAttributeRepository;
import yte.intern.project.EventManagementSystem.usecases.manageevents.repository.EventRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManageApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationCustomAttributeRepository applicationCustomAttributeRepository;


}
