package cn.edu.upc.eduroamcontrolsystembackend.dao.primary;

import cn.edu.upc.eduroamcontrolsystembackend.model.primary.Notification;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jay on 2018/08/12
 */

public interface NotificationDAO extends CrudRepository<Notification, Integer> {
    Iterable<Notification> findAllByReceiverAndReceiverDeletedIsFalse(String receiver);

    Iterable<Notification> findAllBySenderAndSenderDeletedIsFalse(String sender);
}
