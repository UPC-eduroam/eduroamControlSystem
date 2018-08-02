package cn.edu.upc.eduroamcontrolsystembackend.dao;

import cn.edu.upc.eduroamcontrolsystembackend.model.AdminOperationLog;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

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

