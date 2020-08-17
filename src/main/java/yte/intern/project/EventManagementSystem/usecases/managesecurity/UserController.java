package yte.intern.project.EventManagementSystem.usecases.managesecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event-managers")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public List<String> getAllEventManagers(){
        return null;
    }
}
