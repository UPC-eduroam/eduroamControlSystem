package cn.edu.upc.eduroamcontrolsystembackend.service;

import cn.edu.upc.eduroamcontrolsystembackend.dao.AdminDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AdminService
 *
 * @author jay
 * @date 2018/05/02
 */

@Service
public class AdminService {
    @Autowired
    private AdminDAO adminDAO;

    public Admin findByAdminId(int adminId) {
        return adminDAO.findByAdminId(adminId);
    }


}
