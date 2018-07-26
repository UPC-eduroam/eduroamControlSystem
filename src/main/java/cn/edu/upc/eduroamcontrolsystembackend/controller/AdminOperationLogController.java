package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.dto.SwaggerParameter;
import cn.edu.upc.eduroamcontrolsystembackend.service.AdminOperationLogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * AdminOperationLogController
 *
 * @author jay
 * @date 2018/05/02
 */


@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("AdminOperationLogController")
public class AdminOperationLogController {
    @Autowired
    private AdminOperationLogService adminOperationLogService;

    @ApiOperation("获取指定管理员的操作记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, value = "token", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "adminId", value = "管理员用户Id", required = true, dataType = "String")
    })
    @GetMapping("/GetAllAdminOperationLogsByAdminId")
    public Object getAllAdminOperationLogsByAdminId(String adminId) {
        return adminOperationLogService.findAllByAdminId(adminId);
    }
}
