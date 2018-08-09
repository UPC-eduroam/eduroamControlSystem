package cn.edu.upc.eduroamcontrolsystembackend.dao.primary;

import cn.edu.upc.eduroamcontrolsystembackend.model.primary.AdminOperationLog;
import org.springframework.data.repository.CrudRepository;

/**
 * AdminOperationLogDAO
 *
 * @author jay
 * @date 2018/05/02
 */

public interface AdminOperationLogDAO extends CrudRepository<AdminOperationLog, Integer> {
    Iterable<AdminOperationLog> findAllByAdminId(String adminId);

    Iterable<AdminOperationLog> findAllByAdminIdAndOperatingTimeBetween(String adminId, String startDate, String endDate);

}

