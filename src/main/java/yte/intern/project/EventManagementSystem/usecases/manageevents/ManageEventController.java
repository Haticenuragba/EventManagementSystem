package yte.intern.project.EventManagementSystem.usecases.manageevents;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.project.EventManagementSystem.common.mapper.CycleAvoidingMappingContext;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.CustomAttributeDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.EventDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.CustomAttribute;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;
import yte.intern.project.EventManagementSystem.usecases.manageevents.mapper.CustomAttributeMapper;
import yte.intern.project.EventManagementSystem.usecases.manageevents.mapper.EventMapper;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/events")
public class ManageEventController {
    private final ManageEventService manageEventService;
    private final EventMapper eventMapper;
    private final CustomAttributeMapper customAttributeMapper;

    @PostMapping
    public EventDTO addEvent(@Valid @RequestBody EventDTO eventDTO) {
        Event event = eventMapper.mapToEntity(eventDTO, new CycleAvoidingMappingContext());
        Event addedEvent = manageEventService.addEvent(event);
        return eventMapper.mapToDto(addedEvent, new CycleAvoidingMappingContext());
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        List<Event> events = manageEventService.getAllEvents();
        return eventMapper.mapToDto(events, new CycleAvoidingMappingContext());
    }

    @GetMapping("/{title}")
    public EventDTO getEventByEventTitle(@PathVariable @Size(max = 255, min = 3) String title) {
        Event event = manageEventService.getEventByTitle(title);
        return eventMapper.mapToDto(event, new CycleAvoidingMappingContext());
    }

    @GetMapping("/{title}/attributes")
    public List<CustomAttributeDTO> getEventCustomAttributes(@PathVariable @Size(max = 255, min = 3) String title) {
        List<CustomAttribute> customAttributes = manageEventService.getEventCustomAttributes(title);
        return customAttributeMapper.mapToDto(customAttributes);
    }

    @PutMapping("/{title}")
    public EventDTO updateEvent(@PathVariable @Size(max = 255, min = 3) String title, @Valid @RequestBody EventDTO eventDTO) {
        Event event = eventMapper.mapToEntity(eventDTO, new CycleAvoidingMappingContext());
        System.out.println(event);
        Event updatedEvent = manageEventService.updateEvent(title, event);
        return eventMapper.mapToDto(updatedEvent, new CycleAvoidingMappingContext());
    }

    @DeleteMapping("/{title}")
    public void deleteEvent(@PathVariable @Size(max = 255, min = 3) String title) {
        manageEventService.deleteEvent(title);
    }



}
