package org.cmy.community.cache;

import lombok.Data;
import org.cmy.community.dto.HotTagDTO;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class HotTagCache {
    private Map<String, Integer> tags = new HashMap<>();
    private List<String> hots = new ArrayList<>();

    /**
     * 实现top-N
     * @param tags
     */
    public void updateTags(Map<String, Integer> tags){
        //定优先队列的容量
        int max = 5;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);
        tags.forEach((name,priority)->{
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            if (priorityQueue.size()<max){
                priorityQueue.offer(hotTagDTO);
            } else {
                //若当前标签的热度大于 优先队列中热度最小的标签，就替换进去。
                HotTagDTO minHot = priorityQueue.peek();
                if (minHot.compareTo(hotTagDTO) < 0){
                    priorityQueue.poll();
                    priorityQueue.offer(hotTagDTO);
                }
            }
        });
        List<String> sortedTags = new ArrayList<>();
        HotTagDTO poll = priorityQueue.poll();
        while (poll != null){
            sortedTags.add(0,poll.getName());//每次把堆顶的元素插入数组首位，因为优先队列默认是小顶堆。这样就得到了TOP-N
            poll = priorityQueue.poll();
        }
        hots = sortedTags;
    }

}
