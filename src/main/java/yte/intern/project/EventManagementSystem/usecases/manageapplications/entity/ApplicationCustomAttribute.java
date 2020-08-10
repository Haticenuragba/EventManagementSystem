package yte.intern.project.EventManagementSystem.usecases.manageapplications.entity;

import lombok.*;
import yte.intern.project.EventManagementSystem.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.util.Objects;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "idgen", sequenceName = "APPLICATION_CUSTOM_ATTRIBUTE_SEQ")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApplicationCustomAttribute extends BaseEntity {

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "ANSWER")
    private String answer;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationCustomAttribute)) return false;
        if (!super.equals(o)) return false;
        ApplicationCustomAttribute that = (ApplicationCustomAttribute) o;
        return question.equals(that.question) &&
                Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), question, answer);
    }
}
