package yte.intern.project.EventManagementSystem.usecases.manageapplications;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.objects.LatestApplication;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class ManageNotificationController {

    private final ManageApplicationService manageApplicationService;

    @CrossOrigin(allowedHeaders = "*")
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<LatestApplication> getResourceUsage() {

        return Flux.interval(Duration.ofSeconds(1))
                .map(it ->
                        manageApplicationService.getLatestApplication()
                );

    }
}
