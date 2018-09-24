package cn.edu.upc.eduroamcontrolsystembackend.analysis.Schedule;

import cn.edu.upc.eduroamcontrolsystembackend.analysis.util.GetAllAdminEmailAddress;
import cn.edu.upc.eduroamcontrolsystembackend.analysis.util.GetLatestAccessRequests;
import cn.edu.upc.eduroamcontrolsystembackend.analysis.util.GetUserStateInCampusNet;
import cn.edu.upc.eduroamcontrolsystembackend.service.NotificationService;
import cn.edu.upc.eduroamcontrolsystembackend.service.UserService;
import cn.edu.upc.eduroamcontrolsystembackend.util.EmailUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 哨兵,定时轮询获取成功登录eduroam的用户并比对相应用户当前在校园网中的状态
 * Created by jay on 2018/08/20
 */

@Service
@EnableScheduling
public class Sentry {
    @Autowired
    private GetUserStateInCampusNet getUserStateInCampusNet;
    @Autowired
    private GetLatestAccessRequests getLatestAccessRequests;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private GetAllAdminEmailAddress getAllAdminEmailAddress;

    private final Log logger = LogFactory.getLog(this.getClass());

    @Value("${polling-interval}")
    private int interval;

    @Value("${warning2User}")
    private String warning2User;

    @Value("${warning2Admin}")
    private String warning2Admin;

    @Scheduled(cron = "${schedule}")
    public void keepEyesOnRadius() {
        logger.info("--> 哨兵开始此次轮询 <--");
        Date date = new Date();
        Timestamp start = new Timestamp(date.getTime() - 60000 * interval);
        Timestamp end = new Timestamp(date.getTime());
        Iterable<Object> userIds = getLatestAccessRequests.getAllLatestUsernames(start, end);
        int counter = 0;
        int abnormal = 0;
        for (Object oneUserId : userIds) {
            counter++;
            String userId = oneUserId.toString();
            if (getUserStateInCampusNet.getUserOnlineState(userId)) {
                abnormal++;
                logger.info("发现用户 " + userId + " 存在eduroam与校园网同时在线现象");
                notificationService.create("system", userId, warning2User);
                notificationService.create("system", "ADMIN", "发现用户 " + userId + " 存在eduroam与校园网同时在线现象");
                String userEmail = userService.findFirstByUserId(userId).getEmailAddress();
                // 发送警告邮件给用户
                if (userEmail != null)
                    if (EmailUtil.send(userEmail, warning2User))
                        logger.info("已发送warning邮件给用户" + userId);
                    else
                        logger.warn("发送warning邮件给用户" + userId + " 失败");
                else
                    logger.warn("用户" + userId + "未绑定邮箱, 无法发送通知邮件");

                //发送警告邮件给所有管理员
                Map<String, String> emails = getAllAdminEmailAddress.getAll();
                if (emails != null)
                    for (Map.Entry<String, String> email : emails.entrySet()) {
                        if (EmailUtil.send(email.getValue(), warning2Admin))
                            logger.info("已发送warning邮件给管理员" + email.getKey());
                        else
                            logger.warn("发送warning邮件给管理员" + email.getKey() + "失败");
                    }
            }
        }
        logger.info("此次共获取到" + counter + "名最近" + interval + "分钟内登录eduroam的用户,发现" + abnormal + "个异常账号");
        logger.info(">-- 哨兵结束此次轮询 --<\n");
    }
}
