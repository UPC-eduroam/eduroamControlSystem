package cn.edu.upc.eduroamcontrolsystembackend.dao.primary;

import cn.edu.upc.eduroamcontrolsystembackend.model.primary.UserUsageLog;
import org.springframework.data.repository.CrudRepository;

/**
 * UserUsageLogDAO
 *
 * @author jay
 * @date 2018/05/02
 */

public interface UserUsageLogDAO extends CrudRepository<UserUsageLog, Integer> {
    Iterable<UserUsageLog> findAllByUserId(String userId);

    Iterable<UserUsageLog> findAllByUserIdAndOperatingTimeBetween(String userId, String startDate, String endDate);
}
