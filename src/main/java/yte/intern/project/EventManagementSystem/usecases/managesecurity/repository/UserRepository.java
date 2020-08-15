package yte.intern.project.EventManagementSystem.usecases.managesecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUsername(String username);

	void deleteByUsername(String username);

	boolean existsByUsername(String username);

}
