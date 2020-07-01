package org.cmy.community.exception;

public enum CustomizedErrorCode implements ICustomizedErrorCode{
    QUESTION_NOT_FOUND(2001,"问题不存在"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),//对应 评论出去时，被评论的问题/评论已经被删除 的情况
    NO_LOGIN(2003, "请先登录"),
    SYSTEM_ERRO(2004, "对不起，服务器有点问题，正在抢修"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"您所回复的评论消失了")
    ;
    private String message;
    private Integer code;

    CustomizedErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
