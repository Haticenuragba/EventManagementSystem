package yte.intern.project.EventManagementSystem.usecases.manageevents;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yte.intern.project.EventManagementSystem.usecases.manageevents.objects.ApplicationCountByDate;
import yte.intern.project.EventManagementSystem.usecases.manageevents.objects.Attendant;
import yte.intern.project.EventManagementSystem.usecases.manageevents.objects.EventWithAttendantNumber;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/statistics")
public class StatisticsController {
    private final ManageEventService manageEventService;

    @GetMapping("/by-attendant-number")
    public List<EventWithAttendantNumber> getEventWithAttendantNumber(){
        return manageEventService.getEventsWithAttendantNumber();
    }

    @GetMapping("{title}/by-date")
    public List<ApplicationCountByDate> getApplicationCountByDate(@PathVariable String title){
        return manageEventService.getApplicationsByDate(title);
    }

    @GetMapping("{title}/attendants")
    public List<Attendant> getAttendantsOfEvent(@PathVariable String title){
        return manageEventService.getAttendantsOfEvent(title);
    }


}
