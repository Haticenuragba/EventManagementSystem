package yte.intern.project.EventManagementSystem.usecases.manageevents.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import yte.intern.project.EventManagementSystem.common.entity.BaseEntity;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.Application;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "idgen", sequenceName = "EVENT_SEQ")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "applications", callSuper = true)
public class Event extends BaseEntity {

    @Column(name = "TITLE", unique = true)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "QUOTA")
    private Integer quota;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "EVENT_ID")
    private Set<CustomAttribute> customAttributes = new HashSet<CustomAttribute>();

    public void setCustomAttributes(Set<CustomAttribute> customAttributes) {
        this.customAttributes.clear();
        if (customAttributes != null) {
            this.customAttributes.addAll(customAttributes);
        }
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Application> applications;


    public void addApplication(Application application) {
        if (applications != null) {
            this.applications.add(application);
            application.setEvent(this);
        }
    }

}
