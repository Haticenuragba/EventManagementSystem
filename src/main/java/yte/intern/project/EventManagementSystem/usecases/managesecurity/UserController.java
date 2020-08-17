package yte.intern.project.EventManagementSystem.usecases.managesecurity;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;
import yte.intern.project.EventManagementSystem.usecases.managequestions.dto.QuestionDTO;
import yte.intern.project.EventManagementSystem.usecases.managequestions.entity.Question;
import yte.intern.project.EventManagementSystem.usecases.managequestions.mapper.QuestionMapper;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.entity.Users;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.objects.EventManager;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/event-managers")
@RequiredArgsConstructor
public class UserController {

    private final CustomUserDetailsManager customUserDetailsManager;

    private final QuestionMapper questionMapper;

    @GetMapping
    public List<String> getAllEventManagers() {
        return customUserDetailsManager.getAllEventManagers();

    }

    @GetMapping("/{managerName}")
    public Users getEventManager(@PathVariable String managerName) {
        return customUserDetailsManager.getUserByUsername(managerName);

    }

    @PostMapping
    public Users addEventManager(@RequestBody EventManager eventManager) throws MessagingException, IOException, WriterException {
        return customUserDetailsManager.addUser(eventManager.getUsername(), eventManager.getEmail());
    }

    @GetMapping("event/{eventTitle}")
    public List<QuestionDTO> getEventOfManagerByTitle(@PathVariable String eventTitle){
        return questionMapper.mapToDto(customUserDetailsManager.getQuestionsOfEvent(eventTitle));
    }

}
