package org.cmy.community.service;

import org.apache.ibatis.session.RowBounds;
import org.cmy.community.dto.NotificationDTO;
import org.cmy.community.dto.PaginationDTO;
import org.cmy.community.dto.QuestionDTO;
import org.cmy.community.enums.NotificationStatusEnum;
import org.cmy.community.enums.NotificationTypeEnum;
import org.cmy.community.exception.CustomizedErrorCode;
import org.cmy.community.exception.CustomizedException;
import org.cmy.community.mapper.NotificationMapper;
import org.cmy.community.mapper.UserMapper;
import org.cmy.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 获取当前登录用户收到的回复
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        Integer totalPage;
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);
        totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        //获取分页数据
        Integer offset = size * (page - 1);
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        if (notifications.size() == 0){
            return paginationDTO;
        }
        List<NotificationDTO> notificationDTOS = new ArrayList<>();

        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));//返回的是"回复了问题"/"回复了评论" 这俩type
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    /**
     * 返回未读的通知数
     * @param userId
     * @return
     */
    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId).andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    /**
     * 读取通知
     * @param id
     * @param user
     * @return
     */
    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null) {
            throw new CustomizedException(CustomizedErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (notification.getReceiver() != user.getId()) {
            //若通知的接收者和目前登录用户不是同一人 抛出异常
            throw new CustomizedException(CustomizedErrorCode.READ_NOTIFICATION_FAIL);
        }
        //读了之后标记为已读 0未读  1已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return  notificationDTO;
    }
}
