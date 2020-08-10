package yte.intern.project.EventManagementSystem.usecases.manageevents.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.CustomAttributeDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.EventDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.CustomAttribute;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-10T09:39:03+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventDTO mapToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        EventDTO eventDTO = new EventDTO();

        eventDTO.setTitle( event.getTitle() );
        eventDTO.setDescription( event.getDescription() );
        eventDTO.setImage( event.getImage() );
        eventDTO.setStartDate( event.getStartDate() );
        eventDTO.setEndDate( event.getEndDate() );
        eventDTO.setQuota( event.getQuota() );
        eventDTO.setLatitude( event.getLatitude() );
        eventDTO.setLongitude( event.getLongitude() );
        eventDTO.setCustomAttributes( customAttributeSetToCustomAttributeDTOList( event.getCustomAttributes() ) );

        return eventDTO;
    }

    @Override
    public Event mapToEntity(EventDTO eventDTO) {
        if ( eventDTO == null ) {
            return null;
        }

        Event event = new Event();

        event.setCustomAttributes( customAttributeDTOListToCustomAttributeSet( eventDTO.getCustomAttributes() ) );
        event.setTitle( eventDTO.getTitle() );
        event.setDescription( eventDTO.getDescription() );
        event.setImage( eventDTO.getImage() );
        event.setStartDate( eventDTO.getStartDate() );
        event.setEndDate( eventDTO.getEndDate() );
        event.setQuota( eventDTO.getQuota() );
        event.setLatitude( eventDTO.getLatitude() );
        event.setLongitude( eventDTO.getLongitude() );

        return event;
    }

    @Override
    public List<EventDTO> mapToDto(List<Event> eventList) {
        if ( eventList == null ) {
            return null;
        }

        List<EventDTO> list = new ArrayList<EventDTO>( eventList.size() );
        for ( Event event : eventList ) {
            list.add( mapToDto( event ) );
        }

        return list;
    }

    @Override
    public List<Event> mapToEntity(List<EventDTO> eventDTOList) {
        if ( eventDTOList == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( eventDTOList.size() );
        for ( EventDTO eventDTO : eventDTOList ) {
            list.add( mapToEntity( eventDTO ) );
        }

        return list;
    }

    protected CustomAttributeDTO customAttributeToCustomAttributeDTO(CustomAttribute customAttribute) {
        if ( customAttribute == null ) {
            return null;
        }

        CustomAttributeDTO customAttributeDTO = new CustomAttributeDTO();

        customAttributeDTO.setQuestion( customAttribute.getQuestion() );
        customAttributeDTO.setType( customAttribute.getType() );

        return customAttributeDTO;
    }

    protected List<CustomAttributeDTO> customAttributeSetToCustomAttributeDTOList(Set<CustomAttribute> set) {
        if ( set == null ) {
            return null;
        }

        List<CustomAttributeDTO> list = new ArrayList<CustomAttributeDTO>( set.size() );
        for ( CustomAttribute customAttribute : set ) {
            list.add( customAttributeToCustomAttributeDTO( customAttribute ) );
        }

        return list;
    }

    protected CustomAttribute customAttributeDTOToCustomAttribute(CustomAttributeDTO customAttributeDTO) {
        if ( customAttributeDTO == null ) {
            return null;
        }

        CustomAttribute customAttribute = new CustomAttribute();

        customAttribute.setQuestion( customAttributeDTO.getQuestion() );
        customAttribute.setType( customAttributeDTO.getType() );

        return customAttribute;
    }

    protected Set<CustomAttribute> customAttributeDTOListToCustomAttributeSet(List<CustomAttributeDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<CustomAttribute> set = new HashSet<CustomAttribute>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( CustomAttributeDTO customAttributeDTO : list ) {
            set.add( customAttributeDTOToCustomAttribute( customAttributeDTO ) );
        }

        return set;
    }
}
