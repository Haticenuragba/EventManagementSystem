package yte.intern.project.EventManagementSystem.usecases.manageevents;

import com.google.common.hash.Hashing;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import yte.intern.project.EventManagementSystem.common.exceptionhandling.CustomException;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.Application;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.CustomAttribute;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;
import yte.intern.project.EventManagementSystem.usecases.manageevents.objects.ApplicationCountByDate;
import yte.intern.project.EventManagementSystem.usecases.manageevents.objects.Attendant;
import yte.intern.project.EventManagementSystem.usecases.manageevents.objects.EventWithAttendantNumber;
import yte.intern.project.EventManagementSystem.usecases.manageevents.repository.CustomAttributeRepository;
import yte.intern.project.EventManagementSystem.usecases.manageevents.repository.EventRepository;
import yte.intern.project.EventManagementSystem.usecases.managemails.EmailService;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.entity.Users;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.repository.UserRepository;

import javax.mail.MessagingException;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class ManageEventService {
    private final EventRepository eventRepository;
    private final CustomAttributeRepository customAttributeRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public Event addEvent(Event event) {
        if (eventRepository.existsByTitle(event.getTitle())) {
            throw new CustomException("Bu etkinlik adı zaten mevcut");
        }
        Optional<Users> usersOptional = userRepository.findByUsername(event.getManagerName());
        if(usersOptional.isPresent()){
            Users managerFromDb = usersOptional.get();
            event.setManagerName(managerFromDb.getUsername());
            managerFromDb.addEvent(event);
            event.setQuestionUrl(generateQuestionUrl(event.getTitle()));
            return eventRepository.save(event);
        }
        else{
            throw new CustomException("Böyle bir etkinlik sorumlussu bulunamadı.");
        }
    }

    public Event getEventByTitle(String title) {
        return eventRepository.findEventByTitle(title).orElseThrow(EntityNotFoundException::new);
    }

    public Event searchEventByTitle(String title) {
        return eventRepository.findByTitleContaining(title).orElseThrow(EntityNotFoundException::new);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAllByOrderByCreatedDesc();
    }

    public List<CustomAttribute> getEventCustomAttributes(String title) {
        Optional<Event> eventOptional = eventRepository.findEventByTitle(title);
        if (eventOptional.isPresent()) {
            return new ArrayList<CustomAttribute>(eventOptional.get().getCustomAttributes());
        } else {
            throw new EntityNotFoundException();
        }
    }


    @Transactional
    public Event updateEvent(String title, Event event) {
        Optional<Event> eventOptional = eventRepository.findEventByTitle(title);
        if (eventOptional.isPresent()) {
            if (!title.equals(event.getTitle()) && eventRepository.existsByTitle(event.getTitle())) {
                throw new CustomException("Bu etkinlik adı zaten mevcut");
            }
            updateEventFromDB(event, eventOptional.get());
            return event;
        } else {
            throw new EntityNotFoundException();
        }

    }

    private void updateEventFromDB(Event event, Event eventFromDB) {
        eventFromDB.setTitle(event.getTitle());
        eventFromDB.setStartDate(event.getStartDate());
        eventFromDB.setEndDate(event.getEndDate());
        eventFromDB.setQuota(event.getQuota());
        eventFromDB.setLatitude(event.getLatitude());
        eventFromDB.setImage(event.getImage());
        eventFromDB.setLongitude(event.getLongitude());
        eventFromDB.setDescription(event.getDescription());
        eventFromDB.setCustomAttributes(event.getCustomAttributes());
    }


    public void deleteEvent(String title) throws MessagingException, IOException, WriterException {
        Optional<Event> eventOptional = eventRepository.findEventByTitle(title);
        if (eventOptional.isPresent()) {
            emailService.sendMailForEventCancel(eventOptional.get());
            eventRepository.delete(eventOptional.get());
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

    public List<EventWithAttendantNumber> getEventsWithAttendantNumber(){
        List<Event> events = eventRepository.findAll();
        List<EventWithAttendantNumber> eventWithAttendantNumberList = new ArrayList<EventWithAttendantNumber>();
        for(Event e: events){
            eventWithAttendantNumberList.add(new EventWithAttendantNumber(e.getTitle(), e.getAttendantNumber()));
        }
        return eventWithAttendantNumberList;
    }

    public List<ApplicationCountByDate> getApplicationsByDate(String eventTitle){
        List<Application> applications = getAllApplicationsOfEvent(eventTitle);
        List<ApplicationCountByDate> applicationCountByDateList = new ArrayList<ApplicationCountByDate>();
        Map<String, List<Application>> applicationGrouped = applications.stream().collect(Collectors.groupingBy(a -> a.getCreated().toLocalDate().toString()));
        for(String key: applicationGrouped.keySet()){
            applicationCountByDateList.add(new ApplicationCountByDate(key, applicationGrouped.get(key).size()));
        }
        return applicationCountByDateList;
    }

    public List<Attendant> getAttendantsOfEvent(String eventTitle){
        List<Application> applications = getAllApplicationsOfEvent(eventTitle);
        List<Attendant> attendants = new ArrayList<Attendant>();
        for(Application a: applications){
            attendants.add(new Attendant(a.getIdNumber(), a.getName(), a.getSurname(), a.getEmail()));
        }
        return attendants;
    }

    private String generateQuestionUrl(String title) {
        String stringToHash = title + "trybruteforcefirst";
        return Hashing.sha256()
                .hashString(stringToHash, StandardCharsets.UTF_8)
                .toString();
    }

}
