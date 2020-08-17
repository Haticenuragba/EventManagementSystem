package yte.intern.project.EventManagementSystem.usecases.managequestions.mapper;

import org.mapstruct.Mapper;
import yte.intern.project.EventManagementSystem.usecases.managequestions.dto.QuestionDTO;
import yte.intern.project.EventManagementSystem.usecases.managequestions.entity.Question;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDTO mapToDto(Question question);

    Question mapToEntity(QuestionDTO questionDTO);

    List<QuestionDTO> mapToDto(List<Question> questionList);

    List<Question> mapToEntity(List<QuestionDTO> questionDTOList);
}
