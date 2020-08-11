package yte.intern.project.EventManagementSystem.usecases.manageevents.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@Valid
public class CustomAttributeDTO {

    @NotBlank
    @Size(min = 3, max = 64, message = "Question length should be in between 3-64")
    private String question;

    @NotBlank
    private String type;

    @AssertTrue
    public boolean isTypeValid() {
        return this.type.equals("text") || this.type.equals("number") || this.type.equals("date");
    }

}
