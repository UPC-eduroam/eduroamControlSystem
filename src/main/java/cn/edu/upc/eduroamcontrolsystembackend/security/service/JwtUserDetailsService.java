package cn.edu.upc.eduroamcontrolsystembackend.security.service;

import cn.edu.upc.eduroamcontrolsystembackend.dao.UserDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.User;
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

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // 这里是数据库里的用户类
        User user = userDAO.findFirstByUserId(new Integer(userId));

        if (user == null) {
            throw new UsernameNotFoundException(String.format("没有该用户 '%s'.", userId));
        } else {
            //这里返回上面继承了 UserDetails  接口的用户类,为了简单我们写个工厂类
            return (UserDetails) JwtUserFactory.createJwtUser(user);
        }
    }
}