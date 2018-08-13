package cn.edu.upc.eduroamcontrolsystembackend.dao.primary;

import cn.edu.upc.eduroamcontrolsystembackend.model.primary.Notification;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jay on 2018/08/12
 */

public interface NotificationDAO extends CrudRepository<Notification, Integer> {
    Iterable<Notification> findAllBySendToAndDeletedIsFalse(String sendTo);

    Iterable<Notification> findAllByComeFromAndDeletedIsFalse(String comeFrom);

}
