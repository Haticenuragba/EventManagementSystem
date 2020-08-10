package yte.intern.project.EventManagementSystem.usecases.manageevents.mapper;

import org.mapstruct.Mapper;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.CustomAttributeDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.CustomAttribute;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CustomAttributeMapper {
    CustomAttributeDTO mapToDto(CustomAttribute customAttribute);

    CustomAttribute mapToEntity(CustomAttributeDTO customAttributeDTO);

    List<CustomAttributeDTO> mapToDto(List<CustomAttribute> customAttributeList);

    List<CustomAttribute> mapToEntity(List<CustomAttributeDTO> customAttributeDTOList);
}
