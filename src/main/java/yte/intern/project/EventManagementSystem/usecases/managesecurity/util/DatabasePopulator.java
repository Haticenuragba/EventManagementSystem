package yte.intern.project.EventManagementSystem.usecases.managesecurity.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.CustomUserDetailsManager;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.entity.Authority;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.entity.Users;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.repository.AuthorityRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DatabasePopulator {

    private final CustomUserDetailsManager customUserDetailsManager;
    private final AuthorityRepository authorityRepository;

    @Transactional
    public void populateDatabaseAfterInit() {

        List<Authority> savedAuthorities = authorityRepository.saveAll(Set.of(new Authority(null, "ADMIN"), new Authority(null, "EVENT_MANAGER")));
        Users adminUser = new Users(null, "admin", "1478963", Set.copyOf(savedAuthorities));
        Users manager1 = new Users(null, "haticenuragba", "1478963", Set.of(new Authority(null, "EVENT_MANAGER")));
        Users manager2 = new Users(null, "serhatyilmaz", "1478963", Set.of(new Authority(null, "EVENT_MANAGER")));

        customUserDetailsManager.createUser(adminUser);
        customUserDetailsManager.createUser(manager1);
        customUserDetailsManager.createUser(manager2);
    }
}
