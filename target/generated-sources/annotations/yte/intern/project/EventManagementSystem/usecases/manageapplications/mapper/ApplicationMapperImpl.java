package yte.intern.project.EventManagementSystem.usecases.manageapplications.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.EventManagementSystem.common.mapper.CycleAvoidingMappingContext;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.dto.ApplicationCustomAttributeDTO;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.dto.ApplicationDTO;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.Application;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.ApplicationCustomAttribute;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.CustomAttributeDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.EventDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.CustomAttribute;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-13T13:06:24+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14 (Oracle Corporation)"
)
@Component
public class ApplicationMapperImpl implements ApplicationMapper {

    @Override
    public ApplicationDTO mapToDto(Application application, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        ApplicationDTO target = cycleAvoidingMappingContext.getMappedInstance( application, ApplicationDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( application == null ) {
            return null;
        }

        ApplicationDTO applicationDTO = new ApplicationDTO();

        cycleAvoidingMappingContext.storeMappedInstance( application, applicationDTO );

        applicationDTO.setIdNumber( application.getIdNumber() );
        applicationDTO.setEmail( application.getEmail() );
        applicationDTO.setName( application.getName() );
        applicationDTO.setSurname( application.getSurname() );
        applicationDTO.setEvent( eventToEventDTO( application.getEvent(), cycleAvoidingMappingContext ) );
        applicationDTO.setApplicationCustomAttributes( applicationCustomAttributeSetToApplicationCustomAttributeDTOList( application.getApplicationCustomAttributes(), cycleAvoidingMappingContext ) );

        return applicationDTO;
    }

    @Override
    public Application mapToEntity(ApplicationDTO applicationDTO, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        Application target = cycleAvoidingMappingContext.getMappedInstance( applicationDTO, Application.class );
        if ( target != null ) {
            return target;
        }

        if ( applicationDTO == null ) {
            return null;
        }

        Application application = new Application();

        cycleAvoidingMappingContext.storeMappedInstance( applicationDTO, application );

        application.setApplicationCustomAttributes( applicationCustomAttributeDTOListToApplicationCustomAttributeSet( applicationDTO.getApplicationCustomAttributes(), cycleAvoidingMappingContext ) );
        application.setIdNumber( applicationDTO.getIdNumber() );
        application.setEmail( applicationDTO.getEmail() );
        application.setName( applicationDTO.getName() );
        application.setSurname( applicationDTO.getSurname() );
        application.setEvent( eventDTOToEvent( applicationDTO.getEvent(), cycleAvoidingMappingContext ) );

        return application;
    }

    @Override
    public List<ApplicationDTO> mapToDto(List<Application> applicationList, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<ApplicationDTO> target = cycleAvoidingMappingContext.getMappedInstance( applicationList, List.class );
        if ( target != null ) {
            return target;
        }

        if ( applicationList == null ) {
            return null;
        }

        List<ApplicationDTO> list = new ArrayList<ApplicationDTO>( applicationList.size() );
        cycleAvoidingMappingContext.storeMappedInstance( applicationList, list );

        for ( Application application : applicationList ) {
            list.add( mapToDto( application, cycleAvoidingMappingContext ) );
        }

        return list;
    }

    @Override
    public List<Application> mapToEntity(List<ApplicationDTO> applicationDTOList, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<Application> target = cycleAvoidingMappingContext.getMappedInstance( applicationDTOList, List.class );
        if ( target != null ) {
            return target;
        }

        if ( applicationDTOList == null ) {
            return null;
        }

        List<Application> list = new ArrayList<Application>( applicationDTOList.size() );
        cycleAvoidingMappingContext.storeMappedInstance( applicationDTOList, list );

        for ( ApplicationDTO applicationDTO : applicationDTOList ) {
            list.add( mapToEntity( applicationDTO, cycleAvoidingMappingContext ) );
        }

        return list;
    }

    protected CustomAttributeDTO customAttributeToCustomAttributeDTO(CustomAttribute customAttribute, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        CustomAttributeDTO target = cycleAvoidingMappingContext.getMappedInstance( customAttribute, CustomAttributeDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( customAttribute == null ) {
            return null;
        }

        CustomAttributeDTO customAttributeDTO = new CustomAttributeDTO();

        cycleAvoidingMappingContext.storeMappedInstance( customAttribute, customAttributeDTO );

        customAttributeDTO.setQuestion( customAttribute.getQuestion() );
        customAttributeDTO.setType( customAttribute.getType() );

        return customAttributeDTO;
    }

    protected List<CustomAttributeDTO> customAttributeSetToCustomAttributeDTOList(Set<CustomAttribute> set, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<CustomAttributeDTO> target = cycleAvoidingMappingContext.getMappedInstance( set, List.class );
        if ( target != null ) {
            return target;
        }

        if ( set == null ) {
            return null;
        }

        List<CustomAttributeDTO> list = new ArrayList<CustomAttributeDTO>( set.size() );
        cycleAvoidingMappingContext.storeMappedInstance( set, list );

        for ( CustomAttribute customAttribute : set ) {
            list.add( customAttributeToCustomAttributeDTO( customAttribute, cycleAvoidingMappingContext ) );
        }

        return list;
    }

    protected EventDTO eventToEventDTO(Event event, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        EventDTO target = cycleAvoidingMappingContext.getMappedInstance( event, EventDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( event == null ) {
            return null;
        }

        EventDTO eventDTO = new EventDTO();

        cycleAvoidingMappingContext.storeMappedInstance( event, eventDTO );

        eventDTO.setTitle( event.getTitle() );
        eventDTO.setDescription( event.getDescription() );
        eventDTO.setImage( event.getImage() );
        eventDTO.setStartDate( event.getStartDate() );
        eventDTO.setEndDate( event.getEndDate() );
        eventDTO.setQuota( event.getQuota() );
        eventDTO.setLatitude( event.getLatitude() );
        eventDTO.setLongitude( event.getLongitude() );
        eventDTO.setCustomAttributes( customAttributeSetToCustomAttributeDTOList( event.getCustomAttributes(), cycleAvoidingMappingContext ) );
        eventDTO.setAttendantNumber( event.getAttendantNumber() );
        eventDTO.setApplications( mapToDto( event.getApplications(), cycleAvoidingMappingContext ) );

        return eventDTO;
    }

    protected ApplicationCustomAttributeDTO applicationCustomAttributeToApplicationCustomAttributeDTO(ApplicationCustomAttribute applicationCustomAttribute, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        ApplicationCustomAttributeDTO target = cycleAvoidingMappingContext.getMappedInstance( applicationCustomAttribute, ApplicationCustomAttributeDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( applicationCustomAttribute == null ) {
            return null;
        }

        ApplicationCustomAttributeDTO applicationCustomAttributeDTO = new ApplicationCustomAttributeDTO();

        cycleAvoidingMappingContext.storeMappedInstance( applicationCustomAttribute, applicationCustomAttributeDTO );

        applicationCustomAttributeDTO.setQuestion( applicationCustomAttribute.getQuestion() );
        applicationCustomAttributeDTO.setType( applicationCustomAttribute.getType() );
        applicationCustomAttributeDTO.setAnswer( applicationCustomAttribute.getAnswer() );

        return applicationCustomAttributeDTO;
    }

    protected List<ApplicationCustomAttributeDTO> applicationCustomAttributeSetToApplicationCustomAttributeDTOList(Set<ApplicationCustomAttribute> set, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<ApplicationCustomAttributeDTO> target = cycleAvoidingMappingContext.getMappedInstance( set, List.class );
        if ( target != null ) {
            return target;
        }

        if ( set == null ) {
            return null;
        }

        List<ApplicationCustomAttributeDTO> list = new ArrayList<ApplicationCustomAttributeDTO>( set.size() );
        cycleAvoidingMappingContext.storeMappedInstance( set, list );

        for ( ApplicationCustomAttribute applicationCustomAttribute : set ) {
            list.add( applicationCustomAttributeToApplicationCustomAttributeDTO( applicationCustomAttribute, cycleAvoidingMappingContext ) );
        }

        return list;
    }

    protected ApplicationCustomAttribute applicationCustomAttributeDTOToApplicationCustomAttribute(ApplicationCustomAttributeDTO applicationCustomAttributeDTO, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        ApplicationCustomAttribute target = cycleAvoidingMappingContext.getMappedInstance( applicationCustomAttributeDTO, ApplicationCustomAttribute.class );
        if ( target != null ) {
            return target;
        }

        if ( applicationCustomAttributeDTO == null ) {
            return null;
        }

        ApplicationCustomAttribute applicationCustomAttribute = new ApplicationCustomAttribute();

        cycleAvoidingMappingContext.storeMappedInstance( applicationCustomAttributeDTO, applicationCustomAttribute );

        applicationCustomAttribute.setQuestion( applicationCustomAttributeDTO.getQuestion() );
        applicationCustomAttribute.setType( applicationCustomAttributeDTO.getType() );
        applicationCustomAttribute.setAnswer( applicationCustomAttributeDTO.getAnswer() );

        return applicationCustomAttribute;
    }

    protected Set<ApplicationCustomAttribute> applicationCustomAttributeDTOListToApplicationCustomAttributeSet(List<ApplicationCustomAttributeDTO> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        Set<ApplicationCustomAttribute> target = cycleAvoidingMappingContext.getMappedInstance( list, Set.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        Set<ApplicationCustomAttribute> set = new HashSet<ApplicationCustomAttribute>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        cycleAvoidingMappingContext.storeMappedInstance( list, set );

        for ( ApplicationCustomAttributeDTO applicationCustomAttributeDTO : list ) {
            set.add( applicationCustomAttributeDTOToApplicationCustomAttribute( applicationCustomAttributeDTO, cycleAvoidingMappingContext ) );
        }

        return set;
    }

    protected CustomAttribute customAttributeDTOToCustomAttribute(CustomAttributeDTO customAttributeDTO, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        CustomAttribute target = cycleAvoidingMappingContext.getMappedInstance( customAttributeDTO, CustomAttribute.class );
        if ( target != null ) {
            return target;
        }

        if ( customAttributeDTO == null ) {
            return null;
        }

        CustomAttribute customAttribute = new CustomAttribute();

        cycleAvoidingMappingContext.storeMappedInstance( customAttributeDTO, customAttribute );

        customAttribute.setQuestion( customAttributeDTO.getQuestion() );
        customAttribute.setType( customAttributeDTO.getType() );

        return customAttribute;
    }

    protected Set<CustomAttribute> customAttributeDTOListToCustomAttributeSet(List<CustomAttributeDTO> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        Set<CustomAttribute> target = cycleAvoidingMappingContext.getMappedInstance( list, Set.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        Set<CustomAttribute> set = new HashSet<CustomAttribute>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        cycleAvoidingMappingContext.storeMappedInstance( list, set );

        for ( CustomAttributeDTO customAttributeDTO : list ) {
            set.add( customAttributeDTOToCustomAttribute( customAttributeDTO, cycleAvoidingMappingContext ) );
        }

        return set;
    }

    protected Event eventDTOToEvent(EventDTO eventDTO, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        Event target = cycleAvoidingMappingContext.getMappedInstance( eventDTO, Event.class );
        if ( target != null ) {
            return target;
        }

        if ( eventDTO == null ) {
            return null;
        }

        Event event = new Event();

        cycleAvoidingMappingContext.storeMappedInstance( eventDTO, event );

        event.setCustomAttributes( customAttributeDTOListToCustomAttributeSet( eventDTO.getCustomAttributes(), cycleAvoidingMappingContext ) );
        event.setTitle( eventDTO.getTitle() );
        event.setDescription( eventDTO.getDescription() );
        event.setImage( eventDTO.getImage() );
        event.setStartDate( eventDTO.getStartDate() );
        event.setEndDate( eventDTO.getEndDate() );
        event.setQuota( eventDTO.getQuota() );
        event.setLatitude( eventDTO.getLatitude() );
        event.setLongitude( eventDTO.getLongitude() );
        event.setAttendantNumber( eventDTO.getAttendantNumber() );
        event.setApplications( mapToEntity( eventDTO.getApplications(), cycleAvoidingMappingContext ) );

        return event;
    }
}
