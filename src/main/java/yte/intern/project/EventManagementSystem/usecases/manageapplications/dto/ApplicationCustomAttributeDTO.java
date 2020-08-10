package yte.intern.project.EventManagementSystem.usecases.manageapplications.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class ApplicationCustomAttributeDTO {

    private String question;

    private String type;

    private String answer;
    
}
