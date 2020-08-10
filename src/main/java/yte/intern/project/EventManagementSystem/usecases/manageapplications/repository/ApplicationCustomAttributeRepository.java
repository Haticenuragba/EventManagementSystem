package yte.intern.project.EventManagementSystem.usecases.manageapplications.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.entity.ApplicationCustomAttribute;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

public interface ApplicationCustomAttributeRepository extends JpaRepository<ApplicationCustomAttribute, Long> {
}
