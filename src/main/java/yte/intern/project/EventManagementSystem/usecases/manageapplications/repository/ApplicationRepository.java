package yte.intern.project.EventManagementSystem.usecases.manageapplications.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.Application;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<List<Application>> findApplicationByIdNumber(String idNumber);
    boolean existsByIdNumber(String idNumber);
}

