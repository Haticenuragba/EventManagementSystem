package yte.intern.project.EventManagementSystem.usecases.managequestions.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.project.EventManagementSystem.usecases.managequestions.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
