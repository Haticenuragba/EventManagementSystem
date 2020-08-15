package yte.intern.project.EventManagementSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.util.DatabasePopulator;

import javax.annotation.PostConstruct;

//(exclude = { SecurityAutoConfiguration.class})

@RequiredArgsConstructor
@SpringBootApplication
public class EventManagementSystemApplication {

    private final DatabasePopulator databasePopulator;

	public static void main(String[] args) {
		SpringApplication.run(EventManagementSystemApplication.class, args);
        System.out.println("Try");

    }
/*

    @PostConstruct
    public void initSecurityData() {
        databasePopulator.populateDatabaseAfterInit();
    }
*/


}
