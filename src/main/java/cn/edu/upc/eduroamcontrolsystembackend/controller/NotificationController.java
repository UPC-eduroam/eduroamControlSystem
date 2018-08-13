package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.dao.primary.NotificationDAO;
import cn.edu.upc.eduroamcontrolsystembackend.dto.ResponseMessage;
import cn.edu.upc.eduroamcontrolsystembackend.model.primary.Notification;
import cn.edu.upc.eduroamcontrolsystembackend.service.NotificationService;
import cn.edu.upc.eduroamcontrolsystembackend.service.UserUsageLogService;
import cn.edu.upc.eduroamcontrolsystembackend.util.GetUserAuthority;
import cn.edu.upc.eduroamcontrolsystembackend.util.MyDateFormat;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jay on 2018/08/12
 */

@PreAuthorize("hasAnyRole('ADMIN','USER')")
@RestController
@RequestMapping("NotificationController")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationDAO notificationDAO;
    @Autowired
    private GetUserAuthority getUserAuthority;
    @Autowired
    private UserUsageLogService userUsageLogService;

    @ApiOperation(value = "发送消息给指定用户(学生发送请求给管理员/管理员发送消息给学生)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "comeFrom", value = "发送消息的用户Id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sendTo", value = "接受消息方的用户Id(学生发送给管理员, 则这里直接填Admin)", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "message", value = "消息内容", required = true, dataType = "String"),
    })
    @PostMapping("/CreateNotification")
    public Object createNotification(String comeFrom, String sendTo, String message) {
        if (getUserAuthority.getAuthorityByUserId(comeFrom).contains("USER") && getUserAuthority.getAuthorityByUserId(sendTo).contains("USER")) {
            return new ResponseMessage(-1, "学生只能给管理员发送消息");
        }
        notificationService.create(comeFrom, sendTo, message);
        userUsageLogService.createUserUsageLog(comeFrom, new MyDateFormat().formattedDate(), "发送一条消息给用户" + sendTo);
        return new ResponseMessage(0, "发送成功");
    }

    @ApiOperation(value = "根据comeFrom获取所有消息(用于获取所有当前用户已经发送的消息),如果是管理员则这里直接填Admin")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "comeFrom", value = "comeFrom所指定的用户Id", required = true, dataType = "String"),
    })
    @GetMapping("/GetAllByComeFrom")
    public Iterable<Notification> getAllByComeFrom(String comeFrom) {
        return notificationService.findAllByComeFrom(comeFrom);
    }

    @ApiOperation(value = "根据sendTo获取所有消息(用于获取所有发给当前用户的消息)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "sendTo", value = "sendTo所指定的用户Id(也就是当前用户), 如果是管理员则这里直接填Admin", required = true, dataType = "String"),
    })
    @GetMapping("/GetAllBySendTo")
    public Iterable<Notification> getAllBySendTo(String sendTo) {
        return notificationService.findAllBySendTo(sendTo);
    }

    @ApiOperation(value = "将消息标记为已读")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "notificationId", value = "需要标记的通知的id", required = true, dataType = "int"),
    })
    @PostMapping("/MarkAsViewed")
    public Object markAsViewed(int notificationId) {
        try {
            Notification notification = notificationDAO.findOne(notificationId);
            notification.setViewed(true);
            notificationService.update(notification);
        } catch (Exception e) {
            return new ResponseMessage(-1, "标记失败, 请稍后再试");
        }
        return new ResponseMessage(0, "标记成功");
    }


    @ApiOperation(value = "删除指定消息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "notificationId", value = "需要删除的通知的id", required = true, dataType = "int"),
    })
    @PostMapping("/DeleteNotification")
    public Object markAsDeleted(int notificationId) {
        try {
            Notification notification = notificationDAO.findOne(notificationId);
            notification.setDeleted(true);
            notificationService.update(notification);
        } catch (Exception e) {
            return new ResponseMessage(-1, "删除失败, 请稍后再试");
        }
        return new ResponseMessage(0, "删除成功");
    }

}
