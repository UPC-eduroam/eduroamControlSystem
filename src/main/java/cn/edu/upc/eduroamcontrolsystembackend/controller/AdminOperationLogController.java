package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.service.AdminOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * AdminOperationLogController
 *
 * @author jay
 * @date 2018/05/02
 */

@RestController
@RequestMapping("AdminOperationLogController")
public class AdminOperationLogController {
    @Autowired
    private AdminOperationLogService adminOperationLogService;

    /**
     * 在管理员进行操作后保存操作日志
     *
     * @param adminId       the admin id
     * @param operatingTime the operating time
     * @param level         the level, 操作级别, 系统设置级别/用户权限设置级别
     * @param operation     the operation, 具体操作内容
     */
    public void createAdminOperationLog(int adminId, Date operatingTime, String level, String operation) {
        adminOperationLogService.createAdminOperationLog(adminId, operatingTime, level, operation);
    }
}