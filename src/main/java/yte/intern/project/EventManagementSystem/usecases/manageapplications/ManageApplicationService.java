package yte.intern.project.EventManagementSystem.usecases.manageapplications;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.Application;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.repository.ApplicationCustomAttributeRepository;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.repository.ApplicationRepository;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;
import yte.intern.project.EventManagementSystem.usecases.manageevents.repository.EventRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ManageApplicationService {
    private final EventRepository eventRepository;
    private final ApplicationRepository applicationRepository;
    private final ApplicationCustomAttributeRepository applicationCustomAttributeRepository;

    public Application addApplication(Application application, String eventTitle) {
        Optional<Event> eventOptional = eventRepository.findEventByTitle(eventTitle);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            event.addApplication(application);
            Event savedEvent = eventRepository.save(event);
            return savedEvent
                    .getApplications()
                    .stream()
                    .filter(it -> it.getIdNumber().equals(application.getIdNumber()))
                    .collect(toList())
                    .get(0);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public List<Application> getAllApplicationsOfEvent(String eventTitle) {
        Optional<Event> eventOptional = eventRepository.findEventByTitle(eventTitle);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            return event.getApplications();
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Application getApplicationOfEvent(String eventTitle, String idNumber) {
        Optional<Event> eventOptional = eventRepository.findEventByTitle(eventTitle);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            return event.getApplications()
                    .stream()
                    .filter(it -> it.getIdNumber().equals(idNumber))
                    .collect(toList())
                    .get(0);

        } else {
            throw new EntityNotFoundException();
        }
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public List<Application> getApplicationsByIdNumber(String idNumber) {
        Optional<List<Application>> optionalApplicationList = applicationRepository.findApplicationByIdNumber(idNumber);
        if (optionalApplicationList.isPresent()) {
            return applicationRepository.findApplicationByIdNumber(idNumber).get();
        } else {
            throw new EntityNotFoundException();
        }
    }


}
