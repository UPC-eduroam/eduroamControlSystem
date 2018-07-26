package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.dto.SwaggerParameter;
import cn.edu.upc.eduroamcontrolsystembackend.service.UserUsageLogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * UserUsageLogController
 *
 * @author jay
 * @date 2018/05/02
 */


@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("UserUsageLogController")
public class UserUsageLogController {
    @Autowired
    private UserUsageLogService userUsageLogService;

    @ApiOperation("获取指定用户的所有操作记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, value = "token", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户Id", required = true, dataType = "String")
    })
    @GetMapping("/GetAllUserUsageLogsByUserId")
    public Object getAllUserUsageLogsByUserId(String userId) {
        return userUsageLogService.findAllByUserId(userId);
    }
}

