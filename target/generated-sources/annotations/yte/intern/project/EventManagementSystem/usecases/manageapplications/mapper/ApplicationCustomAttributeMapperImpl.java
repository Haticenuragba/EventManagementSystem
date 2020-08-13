package yte.intern.project.EventManagementSystem.usecases.manageapplications.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.dto.ApplicationCustomAttributeDTO;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.ApplicationCustomAttribute;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-13T13:06:23+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14 (Oracle Corporation)"
)
@Component
public class ApplicationCustomAttributeMapperImpl implements ApplicationCustomAttributeMapper {

    @Override
    public ApplicationCustomAttributeDTO mapToDto(ApplicationCustomAttribute applicationCustomAttribute) {
        if ( applicationCustomAttribute == null ) {
            return null;
        }

        ApplicationCustomAttributeDTO applicationCustomAttributeDTO = new ApplicationCustomAttributeDTO();

        applicationCustomAttributeDTO.setQuestion( applicationCustomAttribute.getQuestion() );
        applicationCustomAttributeDTO.setType( applicationCustomAttribute.getType() );
        applicationCustomAttributeDTO.setAnswer( applicationCustomAttribute.getAnswer() );

        return applicationCustomAttributeDTO;
    }

    @Override
    public ApplicationCustomAttribute mapToEntity(ApplicationCustomAttributeDTO applicationCustomAttributeDTO) {
        if ( applicationCustomAttributeDTO == null ) {
            return null;
        }

        ApplicationCustomAttribute applicationCustomAttribute = new ApplicationCustomAttribute();

        applicationCustomAttribute.setQuestion( applicationCustomAttributeDTO.getQuestion() );
        applicationCustomAttribute.setType( applicationCustomAttributeDTO.getType() );
        applicationCustomAttribute.setAnswer( applicationCustomAttributeDTO.getAnswer() );

        return applicationCustomAttribute;
    }

    @Override
    public List<ApplicationCustomAttributeDTO> mapToDto(List<ApplicationCustomAttribute> applicationCustomAttributeList) {
        if ( applicationCustomAttributeList == null ) {
            return null;
        }

        List<ApplicationCustomAttributeDTO> list = new ArrayList<ApplicationCustomAttributeDTO>( applicationCustomAttributeList.size() );
        for ( ApplicationCustomAttribute applicationCustomAttribute : applicationCustomAttributeList ) {
            list.add( mapToDto( applicationCustomAttribute ) );
        }

        return list;
    }

    @Override
    public List<ApplicationCustomAttribute> mapToEntity(List<ApplicationCustomAttributeDTO> applicationCustomAttributeDTOList) {
        if ( applicationCustomAttributeDTOList == null ) {
            return null;
        }

        List<ApplicationCustomAttribute> list = new ArrayList<ApplicationCustomAttribute>( applicationCustomAttributeDTOList.size() );
        for ( ApplicationCustomAttributeDTO applicationCustomAttributeDTO : applicationCustomAttributeDTOList ) {
            list.add( mapToEntity( applicationCustomAttributeDTO ) );
        }

        return list;
    }
}
