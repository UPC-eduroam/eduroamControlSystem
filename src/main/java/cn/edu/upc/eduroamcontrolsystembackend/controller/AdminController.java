package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminController
 *
 * @author jay
 * @date 2018/05/02
 */

@RestController
@RequestMapping("AdminController")
public class AdminController {
    @Autowired
    private AdminService adminService;


    /**
     * 根据id判断是否是管理员
     *
     * @param adminId the admin id
     * @return the object, true/false
     */
    @GetMapping("/isAdmin")
    public Object isAdmin(int adminId) {
        return adminService.findByAdminId(adminId) != null;
    }

}
