package yte.intern.project.EventManagementSystem.usecases.manageevents.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;
import yte.intern.project.EventManagementSystem.usecases.manageapplications.dto.ApplicationDTO;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@ToString
public class EventDTO {


    @NotBlank
    @Size(min = 3, max = 64, message = "Title length should be in between 3-64")
    private String title;

    @NotBlank
    @Size(min = 16, message = "Description length should be larger than 16")
    private String description;

    @URL
    private String image;

    @FutureOrPresent
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    @Min(value = 1, message = "Quota should be at least 1")
    private Integer quota;

    @Max(value = 90, message = "Latitude cannot be greater than 90")
    @Min(value = -90, message = "Latitude cannot be smaller than -90")
    private Double latitude;

    @Max(value = 180, message = "Longitude cannot be greater than 180")
    @Min(value = -180, message = "Longitude cannot be smaller than -180")
    private Double longitude;

    private List<CustomAttributeDTO> customAttributes;

    private List<ApplicationDTO> applications;

    @AssertTrue
    public boolean isEndDateValid() {
        return this.endDate.isAfter(this.startDate ) || this.endDate.isEqual(this.startDate);
    }

}
