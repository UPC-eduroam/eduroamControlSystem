package cn.edu.upc.eduroamcontrolsystembackend.service;

import cn.edu.upc.eduroamcontrolsystembackend.dao.UserDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @author jay
 * @date 2018/05/02
 */

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    /**
     * 根据id判断是否是用户
     *
     * @param userId the user id
     * @return the object, true/false
     */
    public boolean isUser(int userId) {
        if (findFirstByUserId(userId) == null)
            return false;
        else return true;
    }

    public User findFirstByUserId(int userId) {
        return userDAO.findFirstByUserId(userId);
    }
}
