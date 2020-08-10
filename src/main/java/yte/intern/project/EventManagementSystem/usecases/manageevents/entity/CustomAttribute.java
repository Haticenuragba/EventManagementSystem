package yte.intern.project.EventManagementSystem.usecases.manageevents.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;
import yte.intern.project.EventManagementSystem.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.util.Objects;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "idgen", sequenceName = "CUSTOM_ATTRIBUTE_SEQ")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomAttribute extends BaseEntity {
    @Column(name = "QUESTION")
    private String question;

    @Column(name = "TYPE")
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomAttribute)) return false;
        if (!super.equals(o)) return false;
        CustomAttribute that = (CustomAttribute) o;
        return question.equals(that.question) &&
                type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), question, type);
    }
}
