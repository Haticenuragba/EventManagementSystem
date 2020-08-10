package yte.intern.project.EventManagementSystem.usecases.manageapplications.entity;

import lombok.*;
import yte.intern.project.EventManagementSystem.common.entity.BaseEntity;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.CustomAttribute;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "idgen", sequenceName = "APPLICATION_SEQ")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Application extends BaseEntity {

    @Column(name = "ID_NUMBER", unique = true)
    private String idNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "APPLICATION_ID")
    private Set<ApplicationCustomAttribute> applicationCustomAttributes = new HashSet<ApplicationCustomAttribute>();

    public void setApplicationCustomAttributes(Set<ApplicationCustomAttribute> customAttributes) {
        this.applicationCustomAttributes.clear();
        if (customAttributes != null) {
            this.applicationCustomAttributes.addAll(customAttributes);
        }
    }

    @ManyToOne
    @JoinColumn(name = "EVENT_ID")
    private Event event;
}
