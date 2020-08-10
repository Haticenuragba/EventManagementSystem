package yte.intern.project.EventManagementSystem.usecases.manageevents.mapper;

import org.mapstruct.Mapper;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.EventDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDTO mapToDto(Event event);

    Event mapToEntity(EventDTO eventDTO);

    List<EventDTO> mapToDto(List<Event> eventList);

    List<Event> mapToEntity(List<EventDTO> eventDTOList);
}
