package cn.edu.upc.eduroamcontrolsystembackend.service;

import cn.edu.upc.eduroamcontrolsystembackend.dao.AdminOperationLogDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.AdminOperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * AdminOperationLogService
 *
 * @author jay
 * @date 2018/05/02
 */

@Service
public class AdminOperationLogService {
    @Autowired
    private AdminOperationLogDAO adminOperationLogDAO;

    /**
     * 在管理员进行操作后保存操作日志
     *
     * @param adminId       the admin name
     * @param operatingTime the operating time
     * @param level         the level, 操作级别, 系统设置级别/用户权限设置级别
     * @param operation     the operation, 具体操作内容
     */
    public void createAdminOperationLog(String adminId, Date operatingTime, String level, String operation) {
        AdminOperationLog adminOperationLog = new AdminOperationLog(adminId, operatingTime, level, operation);
        adminOperationLog.setAdminId(adminId);
        adminOperationLog.setOperatingTime(operatingTime);
        adminOperationLog.setLevel(level);
        adminOperationLog.setOperation(operation);
        adminOperationLogDAO.save(adminOperationLog);
    }

    public Iterable<AdminOperationLog> findAllByAdminId(String adminId) {
        return adminOperationLogDAO.findAllByAdminId(adminId);
    }


}
