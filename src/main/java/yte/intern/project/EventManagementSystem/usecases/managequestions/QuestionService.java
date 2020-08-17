package yte.intern.project.EventManagementSystem.usecases.managequestions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.project.EventManagementSystem.common.exceptionhandling.CustomException;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;
import yte.intern.project.EventManagementSystem.usecases.manageevents.repository.EventRepository;
import yte.intern.project.EventManagementSystem.usecases.managequestions.dto.QuestionDTO;
import yte.intern.project.EventManagementSystem.usecases.managequestions.entity.Question;
import yte.intern.project.EventManagementSystem.usecases.managequestions.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final EventRepository eventRepository;
    private final QuestionRepository questionRepository;

    public String getEventTitleByQuestionUrl(String url){
        Optional<Event> eventOptional = eventRepository.findEventByQuestionUrl(url);
        if(eventOptional.isPresent()){
            return eventOptional.get().getTitle();
        }
        else {
            throw new CustomException("Soru sorma linki geçersiz");
        }
    }

    public Question addQuestionToEvent(Question question, String eventTitle){
        Optional<Event> eventOptional = eventRepository.findEventByTitle(eventTitle);
        if(eventOptional.isPresent()){
           Event eventFromDb = eventOptional.get();
           eventFromDb.addQuestion(question);
           return questionRepository.save(question);
        }
        else {
            throw new CustomException("Böyle bir etkinlik mevcut değil");
        }

    }
 /*   public List<Question> getAllQuestionsOfEvent(String eventTitle){
        Optional<Event> eventOptional = eventRepository.findEventByTitle(eventTitle);
        if(eventOptional.isPresent()){
            return new ArrayList<Question>(eventOptional.get().getQuestions());
        }
        else {
            throw new CustomException("Soru sorma linki geçersiz");
        }
    }*/
}
