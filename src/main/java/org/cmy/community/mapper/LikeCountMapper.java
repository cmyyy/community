package org.cmy.community.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.cmy.community.model.LikeCount;
import org.cmy.community.model.LikeCountExample;

public interface LikeCountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIKECOUNT
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    long countByExample(LikeCountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIKECOUNT
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    int deleteByExample(LikeCountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIKECOUNT
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIKECOUNT
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    int insert(LikeCount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIKECOUNT
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    int insertSelective(LikeCount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIKECOUNT
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    List<LikeCount> selectByExampleWithRowbounds(LikeCountExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIKECOUNT
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    List<LikeCount> selectByExample(LikeCountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIKECOUNT
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    LikeCount selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIKECOUNT
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    int updateByExampleSelective(@Param("record") LikeCount record, @Param("example") LikeCountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIKECOUNT
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    int updateByExample(@Param("record") LikeCount record, @Param("example") LikeCountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIKECOUNT
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    int updateByPrimaryKeySelective(LikeCount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIKECOUNT
     *
     * @mbg.generated Tue Jul 21 22:43:56 CST 2020
     */
    int updateByPrimaryKey(LikeCount record);
}