package yte.intern.project.EventManagementSystem.usecases.manageapplications.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import yte.intern.project.EventManagementSystem.common.mapper.CycleAvoidingMappingContext;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.dto.ApplicationDTO;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.Application;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    ApplicationDTO mapToDto(Application application, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    Application mapToEntity(ApplicationDTO applicationDTO, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    List<ApplicationDTO> mapToDto(List<Application> applicationList, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    List<Application> mapToEntity(List<ApplicationDTO> applicationDTOList, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
