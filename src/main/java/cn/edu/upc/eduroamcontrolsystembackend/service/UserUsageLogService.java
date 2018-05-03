package cn.edu.upc.eduroamcontrolsystembackend.service;

import cn.edu.upc.eduroamcontrolsystembackend.dao.UserUsageLogDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.UserUsageLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * UserUsageLogService
 *
 * @author jay
 * @date 2018/05/02
 */

@Service
public class UserUsageLogService {
    @Autowired
    private UserUsageLogDAO userUsageLogDAO;

    public void createUserUsageLog(int userId, Date loginTime, String orgDomain) {
        UserUsageLog userUsageLog = new UserUsageLog(userId, loginTime, orgDomain);
        userUsageLogDAO.save(userUsageLog);
    }

    public Iterable<UserUsageLog> findAllByUserId(int userId) {
        return userUsageLogDAO.findAllByUserId(userId);
    }

}
