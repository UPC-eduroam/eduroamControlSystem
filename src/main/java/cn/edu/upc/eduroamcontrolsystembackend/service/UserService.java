package cn.edu.upc.eduroamcontrolsystembackend.service;

import cn.edu.upc.eduroamcontrolsystembackend.dao.primary.UserDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.primary.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jay on 2018/08/03
 */

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public User findFirstByUserId(String userId) {
        return userDAO.findFirstByUserId(userId);
    }

    public User findFirstByEmailAddress(String emailAddress) {
        return userDAO.findFirstByEmailAddress(emailAddress);
    }

    public void update(User user) {
        userDAO.save(user);
    }
}
