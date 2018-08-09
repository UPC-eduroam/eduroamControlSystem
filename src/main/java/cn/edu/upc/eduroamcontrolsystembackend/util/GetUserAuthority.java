package cn.edu.upc.eduroamcontrolsystembackend.util;

import cn.edu.upc.eduroamcontrolsystembackend.model.primary.User;
import cn.edu.upc.eduroamcontrolsystembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jay on 2018/08/04
 */
@Service
public class GetUserAuthority {
    @Autowired
    private UserService userService;

    public String getAuthorityByUserId(String userId) {
        User user = userService.findFirstByUserId(userId);
        return user.getAuthorities().get(0).getAuthorityType().toString();
    }
}
