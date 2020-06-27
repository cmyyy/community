package org.cmy.community.dto;

import lombok.Data;
import org.cmy.community.model.User;

/**
 * 包括Question信息和User信息
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
