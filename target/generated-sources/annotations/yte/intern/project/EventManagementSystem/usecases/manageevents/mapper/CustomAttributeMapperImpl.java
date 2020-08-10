package yte.intern.project.EventManagementSystem.usecases.manageevents.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.CustomAttributeDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.CustomAttribute;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-10T09:39:03+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14 (Oracle Corporation)"
)
@Component
public class CustomAttributeMapperImpl implements CustomAttributeMapper {

    @Override
    public CustomAttributeDTO mapToDto(CustomAttribute customAttribute) {
        if ( customAttribute == null ) {
            return null;
        }

        CustomAttributeDTO customAttributeDTO = new CustomAttributeDTO();

        customAttributeDTO.setQuestion( customAttribute.getQuestion() );
        customAttributeDTO.setType( customAttribute.getType() );

        return customAttributeDTO;
    }

    @Override
    public CustomAttribute mapToEntity(CustomAttributeDTO customAttributeDTO) {
        if ( customAttributeDTO == null ) {
            return null;
        }

        CustomAttribute customAttribute = new CustomAttribute();

        customAttribute.setQuestion( customAttributeDTO.getQuestion() );
        customAttribute.setType( customAttributeDTO.getType() );

        return customAttribute;
    }

    @Override
    public List<CustomAttributeDTO> mapToDto(List<CustomAttribute> customAttributeList) {
        if ( customAttributeList == null ) {
            return null;
        }

        List<CustomAttributeDTO> list = new ArrayList<CustomAttributeDTO>( customAttributeList.size() );
        for ( CustomAttribute customAttribute : customAttributeList ) {
            list.add( mapToDto( customAttribute ) );
        }

        return list;
    }

    @Override
    public List<CustomAttribute> mapToEntity(List<CustomAttributeDTO> customAttributeDTOList) {
        if ( customAttributeDTOList == null ) {
            return null;
        }

        List<CustomAttribute> list = new ArrayList<CustomAttribute>( customAttributeDTOList.size() );
        for ( CustomAttributeDTO customAttributeDTO : customAttributeDTOList ) {
            list.add( mapToEntity( customAttributeDTO ) );
        }

        return list;
    }
}
