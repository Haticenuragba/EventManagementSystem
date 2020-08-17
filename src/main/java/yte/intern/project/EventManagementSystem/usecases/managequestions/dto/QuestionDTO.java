package yte.intern.project.EventManagementSystem.usecases.managequestions.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class QuestionDTO {
    @NotBlank
    private String nickname;

    @NotBlank
    @Size(max = 255, min = 4, message = "Question lenght should be in between 4-255")
    private String question;
}
