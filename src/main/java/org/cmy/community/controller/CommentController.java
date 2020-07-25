package org.cmy.community.controller;

import org.cmy.community.dto.CommentCreateDTO;
import org.cmy.community.dto.CommentDTO;
import org.cmy.community.dto.ResultDTO;
import org.cmy.community.enums.CommentTypeEnum;
import org.cmy.community.enums.NotificationStatusEnum;
import org.cmy.community.enums.NotificationTypeEnum;
import org.cmy.community.exception.CustomizedErrorCode;
import org.cmy.community.mapper.CommentExtMapper;
import org.cmy.community.mapper.CommentMapper;
import org.cmy.community.mapper.LikeCountMapper;
import org.cmy.community.mapper.NotificationMapper;
import org.cmy.community.model.*;
import org.cmy.community.service.CommentService;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeCountMapper likeCountMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private NotificationMapper notificationMapper;

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

    /**
     * 点赞功能
     * @param id
     * @param request
     * @return
     */
    @Transactional
    @GetMapping("/likeComment")
    @ResponseBody
    public ResultDTO likeQuestion(@RequestParam("id") Long id,HttpServletRequest request){
        Comment dbComment;
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return new ResultDTO().errorOf(CustomizedErrorCode.NO_LOGIN);
        }else {
            dbComment = commentMapper.selectByPrimaryKey(id);
            if(dbComment==null){
                return new ResultDTO().errorOf(CustomizedErrorCode.COMMENT_NOT_FOUND);
            }
            //判断用户是否点赞
            LikeCountExample likeCountexample = new LikeCountExample();
            likeCountexample.createCriteria().
                    andAccountIdEqualTo(user.getId()).
                    andCommentIdEqualTo(id);
            List<LikeCount> likeCounts = likeCountMapper.selectByExample(likeCountexample);
            if(likeCounts.size()>0){
                return new ResultDTO().errorOf(CustomizedErrorCode.COMMENT_LIKE_TWICE);
            }else {
                //向数据库中插入一条记录
                LikeCount likeCount = new LikeCount();
                likeCount.setAccountId(user.getId());
                likeCount.setGmtModified(System.currentTimeMillis());
                likeCount.setGmtCreate(System.currentTimeMillis());
                likeCount.setCommentId(id);
                try {
                    likeCountMapper.insert(likeCount);
                    //点赞增加
                    Comment comment = new Comment();
                    comment.setId(id);
                    comment.setLikeCount((long) 1);
                    commentExtMapper.incLikeCount(comment);
                    //自己给自己点赞不通知
                    if(dbComment.getCommentator()==user.getId()){
                        //通知
                        Notification notification = new Notification();
                        notification.setGmtCreate(System.currentTimeMillis());
                        notification.setType(NotificationTypeEnum.LIKE_COMMENT.getType());
                        notification.setNotifier(user.getId());
                        notification.setReceiver(dbComment.getCommentator());
                        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
                        notification.setOuterid(dbComment.getParentId());
                        notification.setNotifierName(user.getName());
                        notification.setOuterTitle(dbComment.getContent());
                        notificationMapper.insertSelective(notification);
                    }
                } catch (Exception e) {
                    return new ResultDTO().errorOf(CustomizedErrorCode.COMMENT_LIKE_FAIL);
                }
            }
        }
        return new ResultDTO().okOf();

    }


}
