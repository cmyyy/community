package org.cmy.community.dto;


import lombok.Data;

@Data
/**
 * 用于实现topN，找出热门话题
 */
public class HotTagDTO implements Comparable{
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}
