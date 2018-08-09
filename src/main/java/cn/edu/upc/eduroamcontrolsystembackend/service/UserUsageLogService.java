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
     * @param userId    the user id
     * @param loginTime the login time, 在校外登录eduroam的时间
     * @param orgDomain the orgDomain, 用户所在机构的域名
     */
    public void createUserUsageLog(String userId, String loginTime, String orgDomain) {
        UserUsageLog userUsageLog = new UserUsageLog(userId, loginTime, orgDomain);
        userUsageLog.setUserId(userId);
        userUsageLog.setLoginTime(loginTime);
        userUsageLog.setOrgDomain(orgDomain);
        userUsageLogDAO.save(userUsageLog);
    }

    public Iterable<UserUsageLog> findAllByUserId(String userId) {
        return userUsageLogDAO.findAllByUserId(userId);
    }

    public Iterable<UserUsageLog> findAllByUserIdAndLoginTimeBetween(String userId, String startDate, String endDate) {
        return userUsageLogDAO.findAllByUserIdAndLoginTimeBetween(userId, startDate, endDate);
    }

}
