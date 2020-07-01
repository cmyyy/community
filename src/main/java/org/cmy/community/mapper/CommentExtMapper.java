package org.cmy.community.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.cmy.community.model.Comment;
import org.cmy.community.model.CommentExample;
import org.cmy.community.model.Question;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment record);
}