package cn.edu.upc.eduroamcontrolsystembackend.analysis.util;

import cn.edu.upc.eduroamcontrolsystembackend.dao.primary.AuthorityDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.primary.Authority;
import cn.edu.upc.eduroamcontrolsystembackend.model.primary.AuthorityType;
import cn.edu.upc.eduroamcontrolsystembackend.model.primary.User;
import cn.edu.upc.eduroamcontrolsystembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取所有的管理员邮箱, 用来给管理员发送检查到异常账号时候的警告
 * <p>
 * 这里默认是获取所有的管理员, 你可以根据实际的需要设置返回值为指定的管理员邮箱, 放入map内返回即可
 * <p>
 * Created by jay on 2018/08/24
 */
@Service
public class GetAllAdminEmailAddress {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityDAO authorityDAO;

    public Map<String, String> getAll() {
        Authority authority = authorityDAO.findFirstByAuthorityType(AuthorityType.ROLE_ADMIN);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        List<User> admins = userService.findAllByAuthorities(authorities);
        Map<String, String> emailAddresses = new HashMap<>();
        for (User admin : admins) {
            emailAddresses.put(admin.getUserId(), admin.getEmailAddress());
        }
        return emailAddresses;
    }
}
