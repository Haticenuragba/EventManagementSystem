package yte.intern.project.EventManagementSystem.usecases.manageevents.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import yte.intern.project.EventManagementSystem.common.mapper.CycleAvoidingMappingContext;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.EventDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDTO mapToDto(Event event, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    Event mapToEntity(EventDTO eventDTO, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    List<EventDTO> mapToDto(List<Event> eventList, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    List<Event> mapToEntity(List<EventDTO> eventDTOList, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);


}
