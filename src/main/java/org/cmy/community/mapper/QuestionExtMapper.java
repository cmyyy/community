package org.cmy.community.mapper;

import org.cmy.community.model.Question;

public interface QuestionExtMapper {
    int incCommentCount(Question record);
    int incView(Question record);

}