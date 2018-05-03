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

    public void createAdminOperationLog(int adminId, Date operatingTime, String level, String operation) {
        AdminOperationLog adminOperationLog = new AdminOperationLog(adminId, operatingTime, level, operation);
        adminOperationLogDAO.save(adminOperationLog);
    }

}
