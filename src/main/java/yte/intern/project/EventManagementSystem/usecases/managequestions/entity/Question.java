package yte.intern.project.EventManagementSystem.usecases.managequestions.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.project.EventManagementSystem.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "idgen", sequenceName = "QUESTION_SEQ")
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseEntity {

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "QUESTION")
    private String question;
}
