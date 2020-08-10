package yte.intern.project.EventManagementSystem.usecases.manageevents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventByTitle(String title);
    boolean existsByTitle(String title);
}
