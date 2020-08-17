package yte.intern.project.EventManagementSystem.usecases.manageapplications;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.project.EventManagementSystem.common.exceptionhandling.CustomException;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.Application;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.objects.LatestApplication;
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
            if(doesEventHasEnoughQuota(event)) {
                if(!doesIdNumberExist(application.getIdNumber(), eventTitle)) {
                    event.addApplication(application);
                    Event savedEvent = eventRepository.save(event);
                    return savedEvent
                            .getApplications()
                            .stream()
                            .filter(it -> it.getIdNumber().equals(application.getIdNumber()))
                            .collect(toList())
                            .get(0);
                }
                else{
                    throw new CustomException("Bu etkinliğe zaten kaydoldunuz.");
                }
            }
            else {
                throw new CustomException("Bu etkinliğin kontenjanı dolu");
            }
        } else {
            throw new EntityNotFoundException();
        }
    }



    public List<Application> getApplicationsByIdNumber(String idNumber) {
        Optional<List<Application>> optionalApplicationList = applicationRepository.findApplicationByIdNumber(idNumber);
        if (optionalApplicationList.isPresent()) {
            return applicationRepository.findApplicationByIdNumber(idNumber).get();
        } else {
            throw new EntityNotFoundException();
        }
    }


    public LatestApplication getLatestApplication(){
        Optional<Application> applicationOptional = applicationRepository.findFirstByOrderByCreatedDesc();
        if(applicationOptional.isPresent()){
            Application application = applicationOptional.get();
            return new LatestApplication(application.getId(), application.getEvent().getTitle(), application.getIdNumber(), application.getName(), application.getSurname());
        }
        else{
            return new LatestApplication(null,null, null, null, null);
        }
    }

    public boolean doesEventHasEnoughQuota(Event event){
        return event.getAttendantNumber() < event.getQuota();
    }
    public boolean doesIdNumberExist(String idNumber, String title){
        return applicationRepository.existsByIdNumberAndEventTitle(idNumber, title);
    }
}
