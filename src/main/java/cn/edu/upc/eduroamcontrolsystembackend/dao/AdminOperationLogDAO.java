package cn.edu.upc.eduroamcontrolsystembackend.dao;

import cn.edu.upc.eduroamcontrolsystembackend.model.AdminOperationLog;
import org.springframework.data.repository.CrudRepository;

/**
 * AdminOperationLogDAO
 *
 * @author jay
 * @date 2018/05/02
 */

public interface AdminOperationLogDAO extends CrudRepository<AdminOperationLog, Integer> {
    Iterable<AdminOperationLog> findAllByAdminId(String adminId);
}
