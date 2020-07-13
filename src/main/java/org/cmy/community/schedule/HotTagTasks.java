package org.cmy.community.schedule;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.cmy.community.cache.HotTagCache;
import org.cmy.community.mapper.QuestionMapper;
import org.cmy.community.model.Question;
import org.cmy.community.model.QuestionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class HotTagTasks {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private HotTagCache hotTagCache;
    @Scheduled(fixedRate = 1000 * 60 * 60 * 6)//每六个小时更新一次
    public void hotTagSchedule(){
        log.info("hotTagSchedule start {}",new Date());
        int offset = 0;
        int limit = 10;
        List<Question> list = new ArrayList<>();
        //用于存放 标签 及 标签权重
        Map<String, Integer> priorities = new HashMap<>();
        while (offset == 0 || list.size() == limit){
            //1.查询所有问题
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, limit));
            for (Question question : list) {
                //2.得到该问题的标签数组
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    //3.得到当前标签的权重值
                    Integer priority = priorities.get(tag);
                    if (priority != null) {
                        //自定义的加权公式
                        priorities.put(tag, priority + 1 + question.getCommentCount());
                    }else{
                        Integer commentCount = question.getCommentCount();
                        priorities.put(tag, 1 + commentCount);
                    }
                }
            }
            offset += limit;
        }
        hotTagCache.updateTags(priorities);
        log.info("hotTagSchedule end {}",new Date());
    }
}

