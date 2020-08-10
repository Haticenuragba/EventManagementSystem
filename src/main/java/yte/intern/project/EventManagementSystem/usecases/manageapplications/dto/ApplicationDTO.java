package yte.intern.project.EventManagementSystem.usecases.manageapplications.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.CustomAttributeDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.dto.EventDTO;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
public class ApplicationDTO {

    private String idNumber;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 255, min = 3, message = "Length of name should be in between 3-255")
    private String name;

    @NotBlank
    @Size(max = 255, min = 2, message = "Length of surname should be in between 2-255")
    private String surname;

    private EventDTO event;

    private List<ApplicationCustomAttributeDTO> applicationCustomAttributes;
}
