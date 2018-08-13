package cn.edu.upc.eduroamcontrolsystembackend.service;

import cn.edu.upc.eduroamcontrolsystembackend.dao.primary.UserUsageLogDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.primary.UserUsageLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 创建用户使用日志
     *
     * @param userId        the user id
     * @param operatingTime the operating time, 操作的时间
     * @param operation,    具体操作内容
     */
    public void createUserUsageLog(String userId, String operatingTime, String operation) {
        UserUsageLog userUsageLog = new UserUsageLog(userId, operatingTime, operation);
        userUsageLogDAO.save(userUsageLog);
    }

    public Iterable<UserUsageLog> findAllByUserId(String userId) {
        return userUsageLogDAO.findAllByUserId(userId);
    }

    public Iterable<UserUsageLog> findAllByUserIdAndOperatingTimeBetween(String userId, String startDate, String endDate) {
        return userUsageLogDAO.findAllByUserIdAndOperatingTimeBetween(userId, startDate, endDate);
    }

}
