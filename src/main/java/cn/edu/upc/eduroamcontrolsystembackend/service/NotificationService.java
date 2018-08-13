package cn.edu.upc.eduroamcontrolsystembackend.service;

import cn.edu.upc.eduroamcontrolsystembackend.dao.primary.NotificationDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.primary.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by jay on 2018/08/12
 */

@Service
public class NotificationService {
    @Autowired
    private NotificationDAO notificationDAO;

    public void create(String comeFrom, String sendTo, String message) {
        Notification notification = new Notification(comeFrom, sendTo, message);
        notificationDAO.save(notification);
    }

    public Iterable<Notification> findAllBySendTo(String sendTo) {
        return notificationDAO.findAllBySendToAndDeletedIsFalse(sendTo);
    }

    public Iterable<Notification> findAllByComeFrom(String comeFrom) {
        return notificationDAO.findAllByComeFromAndDeletedIsFalse(comeFrom);
    }

    public void update(Notification notification) {
        notificationDAO.save(notification);
    }

}
