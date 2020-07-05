package org.cmy.community.controller;

import org.cmy.community.dto.CommentCreateDTO;
import org.cmy.community.dto.CommentDTO;
import org.cmy.community.dto.ResultDTO;
import org.cmy.community.enums.CommentTypeEnum;
import org.cmy.community.exception.CustomizedErrorCode;
import org.cmy.community.model.Comment;
import org.cmy.community.model.User;
import org.cmy.community.service.CommentService;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 进行评论,只需要返回是否成功的状态
     * @param commentCreateDTO
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return ResultDTO.errorOf(CustomizedErrorCode.NO_LOGIN);
        }
        if (commentCreateDTO == null || StringUtils.isNullOrEmpty(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizedErrorCode.EMPTY_CONTENT);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment, user);
        return ResultDTO.okOf();
    }

    /**
     * 从服务端获取评论下的评论（二级评论）
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
