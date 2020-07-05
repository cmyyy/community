package org.cmy.community.mapper;

import org.cmy.community.dto.QuestionQueryDTO;
import org.cmy.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incCommentCount(Question record);
    int incView(Question record);
    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}
