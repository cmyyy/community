package org.cmy.community.dto;

import lombok.Data;

/**
 * 往数据库传输的DTO
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
