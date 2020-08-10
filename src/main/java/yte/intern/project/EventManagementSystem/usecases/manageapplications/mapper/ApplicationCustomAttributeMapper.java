package yte.intern.project.EventManagementSystem.usecases.manageapplications.mapper;

import org.mapstruct.Mapper;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.dto.ApplicationCustomAttributeDTO;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.ApplicationCustomAttribute;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationCustomAttributeMapper {
    ApplicationCustomAttributeDTO mapToDto(ApplicationCustomAttribute applicationCustomAttribute);

    ApplicationCustomAttribute mapToEntity(ApplicationCustomAttributeDTO applicationCustomAttributeDTO);

    List<ApplicationCustomAttributeDTO> mapToDto(List<ApplicationCustomAttribute> applicationCustomAttributeList);

    List<ApplicationCustomAttribute> mapToEntity(List<ApplicationCustomAttributeDTO> applicationCustomAttributeDTOList);
}
