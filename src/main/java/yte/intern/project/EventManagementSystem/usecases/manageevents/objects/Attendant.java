package yte.intern.project.EventManagementSystem.usecases.manageevents.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Attendant {
    private String idNumber;
    private String name;
    private String surname;
    private String email;
}
