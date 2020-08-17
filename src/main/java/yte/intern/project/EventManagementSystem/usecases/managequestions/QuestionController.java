package yte.intern.project.EventManagementSystem.usecases.managequestions;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.project.EventManagementSystem.usecases.managequestions.dto.QuestionDTO;
import yte.intern.project.EventManagementSystem.usecases.managequestions.entity.Question;
import yte.intern.project.EventManagementSystem.usecases.managequestions.mapper.QuestionMapper;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping("/{questionUrl}")
    public String getEventByQuestionUrl(@PathVariable String questionUrl){
        return questionService.getEventTitleByQuestionUrl(questionUrl);
    }

    @PostMapping("/{eventTitle}")
    public QuestionDTO addQuestionToEvent(@PathVariable String eventTitle, @Valid @RequestBody QuestionDTO questionDTO){
        Question question = questionMapper.mapToEntity(questionDTO);
        return questionMapper.mapToDto(questionService.addQuestionToEvent(question, eventTitle));
    }


}
