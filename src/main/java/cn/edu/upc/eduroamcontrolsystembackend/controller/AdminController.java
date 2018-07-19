package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.dto.SwaggerParameter;
import cn.edu.upc.eduroamcontrolsystembackend.service.AdminService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @ApiOperation(value = "判断是否是管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query" , name = "adminId",value = "管理员Id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query",name ="reason",value = "是管理员",required = true,dataType = "boolean")
    })
    @GetMapping("/isAdmin")
    public Object isAdmin(Boolean adminId) {
        return adminId= adminService.findByAdminId(adminId);

    }

}
