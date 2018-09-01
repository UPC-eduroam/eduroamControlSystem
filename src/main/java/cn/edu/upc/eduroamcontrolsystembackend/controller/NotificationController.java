package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.dao.primary.NotificationDAO;
import cn.edu.upc.eduroamcontrolsystembackend.dto.ResponseMessage;
import cn.edu.upc.eduroamcontrolsystembackend.model.primary.Notification;
import cn.edu.upc.eduroamcontrolsystembackend.service.NotificationService;
import cn.edu.upc.eduroamcontrolsystembackend.service.UserUsageLogService;
import cn.edu.upc.eduroamcontrolsystembackend.util.GetUserAuthority;
import cn.edu.upc.eduroamcontrolsystembackend.util.GetUserIdFromRequest;
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

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jay on 2018/08/12
 */

@RestController
@RequestMapping("NotificationController")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class NotificationController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GetUserIdFromRequest getUserIdFromRequest;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationDAO notificationDAO;
    @Autowired
    private GetUserAuthority getUserAuthority;
    @Autowired
    private UserUsageLogService userUsageLogService;
    @Autowired
    private MyDateFormat myDateFormat;

    @ApiOperation(value = "发送消息给指定用户(限制:学生只能发送给管理员)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "receiver", value = "接受方的用户Id(学生发送给管理员, 则这里直接填ADMIN)", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "message", value = "消息内容", required = true, dataType = "String"),
    })
    @PostMapping("/CreateNotification")
    public Object createNotification(String receiver, String message) {
        String sender = getUserIdFromRequest.getUserId(request);
        String authority = getUserAuthority.getAuthorityByUserId(sender);
        if (authority.contains("USER") && !receiver.equals("ADMIN"))
            return new ResponseMessage(-1, "学生只能给管理员发送消息");
        if (authority.contains("ADMIN"))
            sender = "ADMIN";
        notificationService.create(sender, receiver, message);
        userUsageLogService.createUserUsageLog(sender, myDateFormat.formattedDate(), "发送一条消息给用户" + receiver);
        return new ResponseMessage(0, "发送成功");
    }

    @ApiOperation(value = "获取所有当前用户已经发送的消息(所有管理员视作一个对象)")
    @GetMapping("/GetAllNotificationsISent")
    public Iterable<Notification> GetAllNotificationsISent() {
        String userId = getUserIdFromRequest.getUserId(request);
        if (getUserAuthority.getAuthorityByUserId(userId).contains("ADMIN"))
            userId = "ADMIN";
        return notificationService.findAllBySender(userId);
    }

    @ApiOperation(value = "获取所有发给当前用户的消息")
    @GetMapping("/GetAllNotificationsSentToMe")
    public Iterable<Notification> GetAllNotificationsSentToMe() {
        String userId = getUserIdFromRequest.getUserId(request);
        if (getUserAuthority.getAuthorityByUserId(userId).contains("ADMIN"))
            userId = "ADMIN";
        return notificationService.findAllByReceiver(userId);
    }

    @ApiOperation(value = "将消息标记为已读")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "notificationId", value = "需要标记的通知的id", required = true, dataType = "int"),
    })
    @PostMapping("/MarkAsViewed")
    public Object markAsViewed(int notificationId) {
        String userId = getUserIdFromRequest.getUserId(request);
        if (getUserAuthority.getAuthorityByUserId(userId).contains("ADMIN"))
            userId = "ADMIN";
        try {
            Notification notification = notificationDAO.findOne(notificationId);
            if (notification.getReceiver().equals(userId)) {
                notification.setViewed(true);
                notificationService.update(notification);
            } else
                return new ResponseMessage(-1, "标记失败");
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
        String userId = getUserIdFromRequest.getUserId(request);
        if (getUserAuthority.getAuthorityByUserId(userId).contains("ADMIN"))
            userId = "ADMIN";
        try {
            Notification notification = notificationDAO.findOne(notificationId);
            if (notification.getReceiver().equals(userId)) {
                notification.setReceiverDeleted(true);
            } else if (notification.getSender().equals(userId)) {
                notification.setSenderDeleted(true);
            } else {
                return new ResponseMessage(-1, "删除失败");
            }
            notificationService.update(notification);
        } catch (Exception e) {
            return new ResponseMessage(-1, "删除失败, 请稍后再试");
        }
        return new ResponseMessage(0, "删除成功");
    }
}
