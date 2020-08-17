package yte.intern.project.EventManagementSystem.usecases.manageevents.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.hash.Hashing;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import yte.intern.project.EventManagementSystem.common.entity.BaseEntity;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.Application;
import yte.intern.project.EventManagementSystem.usecases.managequestions.entity.Question;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.entity.Users;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "idgen", sequenceName = "EVENT_SEQ")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "applications", callSuper = false)
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

    @Column(name = "ATTENDANT_NUMBER")
    private Integer attendantNumber = 0;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "EVENT_ID")
    private Set<CustomAttribute> customAttributes = new HashSet<CustomAttribute>();

    public void setCustomAttributes(Set<CustomAttribute> customAttributes) {
        this.customAttributes.clear();
        if (customAttributes != null) {
            this.customAttributes.addAll(customAttributes);
        }
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "QUESTION_ID")
    private Set<Question> questions = new HashSet<Question>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Application> applications;


    @Column(name = "MANAGER_NAME")
    private String managerName;

    @Column(name = "QUESTION_URL")
    private String questionUrl;


    public void addApplication(Application application) {
        if (applications != null) {
            this.applications.add(application);
            this.attendantNumber++;
            application.setEvent(this);
        }
    }

    public void addQuestion(Question question) {
        if (questions != null) {
            this.questions.add(question);
        }
        else {
           questions = new HashSet<Question>();
           questions.add(question);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        if (!super.equals(o)) return false;
        Event event = (Event) o;
        return title.equals(event.title) &&
                description.equals(event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description);
    }
}
