package yte.intern.project.EventManagementSystem.usecases.manageevents;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.CustomAttribute;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;
import yte.intern.project.EventManagementSystem.usecases.manageevents.repository.CustomAttributeRepository;
import yte.intern.project.EventManagementSystem.usecases.manageevents.repository.EventRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;


@Service
@RequiredArgsConstructor
public class ManageEventService {
    private final EventRepository eventRepository;
    private final CustomAttributeRepository customAttributeRepository;

    public Event addEvent(Event event) {
        if (eventRepository.existsByTitle(event.getTitle())) {
            throw new EntityExistsException();
        }
        return eventRepository.save(event);
    }

    public Event getEventByTitle(String title) {
        return eventRepository.findEventByTitle(title).orElseThrow(EntityNotFoundException::new);
    }

    public Event searchEventByTitle(String title) {
        return eventRepository.findByTitleContaining(title).orElseThrow(EntityNotFoundException::new);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
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
        eventFromDB.setLongitude(event.getLongitude());
        eventFromDB.setDescription(event.getDescription());
        eventFromDB.setCustomAttributes(event.getCustomAttributes());
    }


    public void deleteEvent(String title) {
        Optional<Event> eventOptional = eventRepository.findEventByTitle(title);
        if (eventOptional.isPresent()) {
            eventRepository.delete(eventOptional.get());
        } else {
            throw new EntityNotFoundException();
        }

    }

}
