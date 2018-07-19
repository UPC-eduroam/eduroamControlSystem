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

    public User findByUserId(int userId)
    {
        return userDAO.findByUserId(userId);
    }
}
