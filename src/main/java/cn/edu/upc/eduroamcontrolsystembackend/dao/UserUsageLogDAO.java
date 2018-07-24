package cn.edu.upc.eduroamcontrolsystembackend.dao;

import cn.edu.upc.eduroamcontrolsystembackend.model.UserUsageLog;
import org.springframework.data.repository.CrudRepository;

/**
 * UserUsageLogDAO
 *
 * @author jay
 * @date 2018/05/02
 */

public interface UserUsageLogDAO extends CrudRepository<UserUsageLog, Integer> {
    Iterable<UserUsageLog> findAllByUserId(String userId);
}
