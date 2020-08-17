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


        Authority adminAuthority = authorityRepository.save(new Authority(null, "ADMIN"));
        Users adminUser = new Users(null, "admin", "1478963", Set.of(adminAuthority));

        customUserDetailsManager.createUser(adminUser);
    }
}
