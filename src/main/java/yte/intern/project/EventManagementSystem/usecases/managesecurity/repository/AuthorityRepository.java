package yte.intern.project.EventManagementSystem.usecases.managesecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
