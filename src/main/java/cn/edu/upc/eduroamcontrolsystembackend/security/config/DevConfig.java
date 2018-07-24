package cn.edu.upc.eduroamcontrolsystembackend.security.config;

import cn.edu.upc.eduroamcontrolsystembackend.dao.AuthorityDAO;
import cn.edu.upc.eduroamcontrolsystembackend.dao.UserDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.Authority;
import cn.edu.upc.eduroamcontrolsystembackend.model.AuthorityType;
import cn.edu.upc.eduroamcontrolsystembackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 为开发时方便前端测试而创建一个amdin和一个user用户
 *
 * @author jay
 * @date 2018/07/23
 */

@Component
public class DevConfig {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthorityDAO authorityDAO;

    @PostConstruct
    public void createDevAdmin() {
        Authority authority = authorityDAO.findFirstByAuthorityType(AuthorityType.ROLE_ADMIN);
        if (authority == null) {
            authority = new Authority();
            authority.setAuthorityType(AuthorityType.ROLE_ADMIN);
            authority = authorityDAO.save(authority);
        }

        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);

        User user = userDAO.findFirstByUserId("devAdmin");
        if (user == null) {
            user = new User();
            user.setUserId("devAdmin");
            user.setPassword(new BCryptPasswordEncoder().encode("devAdmin"));
            user.setAuthorities(authorities);
            userDAO.save(user);
        }
    }

    @PostConstruct
    public void createDevUser() {
        Authority authority = authorityDAO.findFirstByAuthorityType(AuthorityType.ROLE_USER);
        if (authority == null) {
            authority = new Authority();
            authority.setAuthorityType(AuthorityType.ROLE_USER);
            authority = authorityDAO.save(authority);
        }

        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);

        User user = userDAO.findFirstByUserId("devUser");
        if (user == null) {
            user = new User();
            user.setUserId("devUser");
            user.setPassword(new BCryptPasswordEncoder().encode("devUser"));
            user.setAuthorities(authorities);
            userDAO.save(user);
        }
    }

}
