package cn.edu.upc.eduroamcontrolsystembackend.controller;


import cn.edu.upc.eduroamcontrolsystembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author jay
 * @date 2018/05/02
 */

@RestController
@RequestMapping("UserController")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据id判断是否是用户
     *
     * @param userId the user id
     * @return the object, true/false
     */
    @GetMapping("/isUser")
    public Object isUser(int userId) {
        return userService.findByUserId(userId) != null;
    }
}
