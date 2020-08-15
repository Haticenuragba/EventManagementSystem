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

        List<Authority> savedAuthorities = authorityRepository.saveAll(Set.of(new Authority(null, "ROLE_ADMIN"), new Authority(null, "ROLE_EVENT_MANAGER")));
        Users adminUser = new Users(null, "admin", "admin", Set.of(savedAuthorities.get(0)));
        Users normalUser = new Users(null, "user", "user", Set.of(savedAuthorities.get(1)));
        Users sysUser = new Users(null, "sys", "sys", Set.of());

        customUserDetailsManager.createUser(adminUser);
        customUserDetailsManager.createUser(normalUser);
        customUserDetailsManager.createUser(sysUser);
    }
}
