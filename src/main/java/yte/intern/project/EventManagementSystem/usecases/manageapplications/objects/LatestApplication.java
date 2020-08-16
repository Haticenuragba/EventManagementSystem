package yte.intern.project.EventManagementSystem.usecases.manageapplications.objects;

import lombok.Getter;

@Getter
public class LatestApplication {
    private String id;
    private String eventTitle;
    private String idNumber;
    private String name;
    private String surname;
    public LatestApplication(String eventTitle, String idNumber, String name, String surname){
        this.eventTitle = eventTitle;
        this.idNumber = idNumber;
        this.name = name;
        this.surname = surname;
        this.id = eventTitle + idNumber;
    }
}
