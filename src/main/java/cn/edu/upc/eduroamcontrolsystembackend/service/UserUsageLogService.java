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
    /**
     * 创建用户使用日志
     *
     * @param userId    the user id
     * @param loginTime the login time, 在校外登录eduroam的时间
     * @param orgDomain the orgDomain, 用户所在机构的域名
     */
    public UserUsageLog createUserUsageLog(int userId, Date loginTime, String orgDomain) {
        UserUsageLog userUsageLog = new UserUsageLog(userId, loginTime, orgDomain);
        userUsageLog.setUserId(userId);
        userUsageLog.setLoginTime(loginTime);
        userUsageLog.setOrgDomain(orgDomain);
        return userUsageLogDAO.save(userUsageLog);
    }

    public Iterable<UserUsageLog> findAllByUserId(int userId) {
        return userUsageLogDAO.findAllByUserId(userId);
    }

}
