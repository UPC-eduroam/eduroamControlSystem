package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.dto.SwaggerParameter;
import cn.edu.upc.eduroamcontrolsystembackend.service.AdminOperationLogService;
import cn.edu.upc.eduroamcontrolsystembackend.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * BlackListController
 *
 * @author jay
 * @date 2018/05/02
 */

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("BlackListController")
public class BlacklistController {
    @Autowired
    private BlacklistService blacklistService;

    @Autowired
    private AdminOperationLogService adminOperationLogService;

    @ApiOperation(value = "添加用户到黑名单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, value = "token", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "需要添加进黑名单的用户Id", required = true, dataType = "String"),
    })
    @PostMapping("/AddUserToBlacklist")
    public Object addUserToBlacklist(HttpServletRequest request) {
        String adminId = request.getParameter("adminId");
        String userId = request.getParameter("userId");
        blacklistService.createBlacklist(userId);
        adminOperationLogService.createAdminOperationLog(adminId, new Date(), "system", "添加用户" + userId + "到黑名单");
        return true;
    }

    @ApiOperation(value = "将用户从黑名单移除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, value = "token", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "需要移除的用户Id", required = true, dataType = "String")
    })
    @PostMapping("/DeleteUserFromBlackList")
    public Object deleteUserFromBlacklist(HttpServletRequest request) {
        String adminId = request.getParameter("adminId");
        String userId = request.getParameter("userId");
        blacklistService.deleteBlacklist(userId);
        adminOperationLogService.createAdminOperationLog(adminId, new Date(), "system", "将用户" + userId + "从黑名单移除");
        return true;
    }

    @ApiOperation(value = "根据userId判断用户是否在黑名单中")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, value = "token", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户Id", required = true, dataType = "String"),
    })
    @GetMapping("/IsInBlacklist")
    public Object isInBlacklist(String userId) {
        return blacklistService.findByUserId(userId) != null;
    }

    @ApiOperation(value = "获取黑名单中所有用户")
    @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String")
    @GetMapping("/GetAllUsersFromBlacklist")
    public Object getAllUsersFromBlacklist() {
        return blacklistService.findAll();
    }


}
