package yte.intern.project.EventManagementSystem.usecases.managesecurity.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
public class EventManager {
    private String username;
    private String email;
}
