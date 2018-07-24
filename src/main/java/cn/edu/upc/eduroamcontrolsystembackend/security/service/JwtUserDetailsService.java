package cn.edu.upc.eduroamcontrolsystembackend.security.service;

import cn.edu.upc.eduroamcontrolsystembackend.dao.UserDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This is description.
 *
 * @author jay
 * @date 2018/07/22
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        // 从数据库里的用户表里查找用户, 用于生成JwtUser
        User user = userDAO.findFirstByUserId(userId);
        logger.info("登录用户检测" + user);

        if (user == null) {
            logger.warn("用户 " + userId + " 不存在");
            throw new UsernameNotFoundException(String.format("没有该用户 '%s'.", userId));
        } else {

            //这里返回上面继承了 UserDetails  接口的用户类,
            JwtUser jwtUser = JwtUserFactory.createJwtUser(user);
            logger.info("jwtUser生成 -> " + jwtUser);

            return jwtUser;
        }
    }
}