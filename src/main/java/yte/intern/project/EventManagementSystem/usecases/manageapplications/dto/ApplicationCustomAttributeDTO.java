package yte.intern.project.EventManagementSystem.usecases.manageapplications.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.AssertTrue;

@Getter
@Setter
@ToString
public class ApplicationCustomAttributeDTO {

    private String question;

    private String type;

    private String answer;

    @AssertTrue
    public boolean isTypeValid() {
        return this.type.equals("text") || this.type.equals("number") || this.type.equals("date");
    }

}
