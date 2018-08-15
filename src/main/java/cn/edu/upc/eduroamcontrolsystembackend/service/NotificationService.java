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

    public void create(String sender, String receiver, String message) {
        Notification notification = new Notification(sender, receiver, message);
        notificationDAO.save(notification);
    }

    public Iterable<Notification> findAllByReceiver(String receiver) {
        return notificationDAO.findAllByReceiverAndReceiverDeletedIsFalse(receiver);
    }

    public Iterable<Notification> findAllBySender(String sender) {
        return notificationDAO.findAllBySenderAndSenderDeletedIsFalse(sender);
    }

    public void update(Notification notification) {
        notificationDAO.save(notification);
    }

}
