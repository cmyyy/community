package org.cmy.community.dto;

import lombok.Data;
import org.cmy.community.model.User;

/**
 * 从服务端接收的DTO，去往前端
 */
@Data
public class CommentDTO {
    private Long id;


    private Long parentId;


    private Integer type;


    private Long commentator;


    private Long gmtCreate;

    private Long gmtModified;

    private Integer commentCount;
    private Long likeCount;

    private String content;

    private User user;

}
