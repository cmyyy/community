package org.cmy.community.model;

public class LikeCount {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LIKECOUNT.ID
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LIKECOUNT.ACCOUNT_ID
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LIKECOUNT.COMMENT_ID
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    private Long commentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LIKECOUNT.GMT_CREATE
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LIKECOUNT.GMT_MODIFIED
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    private Long gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LIKECOUNT.ID
     *
     * @return the value of LIKECOUNT.ID
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LIKECOUNT.ID
     *
     * @param id the value for LIKECOUNT.ID
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LIKECOUNT.ACCOUNT_ID
     *
     * @return the value of LIKECOUNT.ACCOUNT_ID
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LIKECOUNT.ACCOUNT_ID
     *
     * @param accountId the value for LIKECOUNT.ACCOUNT_ID
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LIKECOUNT.COMMENT_ID
     *
     * @return the value of LIKECOUNT.COMMENT_ID
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LIKECOUNT.COMMENT_ID
     *
     * @param commentId the value for LIKECOUNT.COMMENT_ID
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LIKECOUNT.GMT_CREATE
     *
     * @return the value of LIKECOUNT.GMT_CREATE
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LIKECOUNT.GMT_CREATE
     *
     * @param gmtCreate the value for LIKECOUNT.GMT_CREATE
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LIKECOUNT.GMT_MODIFIED
     *
     * @return the value of LIKECOUNT.GMT_MODIFIED
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LIKECOUNT.GMT_MODIFIED
     *
     * @param gmtModified the value for LIKECOUNT.GMT_MODIFIED
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}