package yte.intern.project.EventManagementSystem.usecases.manageevents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.CustomAttribute;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

public interface CustomAttributeRepository extends JpaRepository<CustomAttribute, Long> {
}
