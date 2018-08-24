package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.dto.ResponseMessage;
import cn.edu.upc.eduroamcontrolsystembackend.model.primary.User;
import cn.edu.upc.eduroamcontrolsystembackend.service.UserService;
import cn.edu.upc.eduroamcontrolsystembackend.util.AESCrypt;
import cn.edu.upc.eduroamcontrolsystembackend.util.EmailUtil;
import cn.edu.upc.eduroamcontrolsystembackend.util.GetUserIdFromRequest;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jay on 2018/08/23
 */

@RestController
@RequestMapping("UserController")
public class UserController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;
    @Autowired
    private GetUserIdFromRequest getUserIdFromRequest;

    private final Log logger = LogFactory.getLog(this.getClass());

    @Value("${server.ip}" + ":" + "${server.port}")
    private String target;

    @ApiOperation(value = "用于所有用户绑定邮箱")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "emailAddress", value = "邮箱地址", required = true, dataType = "String"),
    })
    @PostMapping("/BindEmail")
    public Object bindEmail(String emailAddress) {
        if (userService.findFirstByEmailAddress(emailAddress) != null)
            return new ResponseMessage(-1, "操作失败! 该邮箱已被绑定!");
        String userId = getUserIdFromRequest.getUserId(request);
        long generateDate = new Date().getTime();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("emailAddress", emailAddress);
        map.put("generateDate", generateDate);
        String json = JSON.toJSONString(map);
        String secret = AESCrypt.encrypt(json);
        System.out.println("email secret: " + secret);
        String url = "http://" + target + "/UserController/VerifyEmail?secret=" + secret;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("您好，</br>").
                append("这是eduroam安全管控系统里绑定邮箱的验证邮件。</br>").
                append("若要最终验证您的电子邮箱地址, 请点击以下链接验证邮箱，48小时内有效，请尽快验证。</br>").
                append("<a href=\"").
                append(url).
                append("\">").
                append(url).
                append("</a></br>").
                append("如果单击链接无效，您可以将此链接复制到浏览器窗口，或在浏览器窗口中直接输入。");
        if (EmailUtil.send(emailAddress, stringBuffer.toString())) {
            logger.info("已发送用来绑定邮箱的验证邮件到" + emailAddress);
            return new ResponseMessage(0, "系统已发送验证邮件,请去邮箱查看并进行验证");
        } else
            return new ResponseMessage(-1, "系统发送邮件失败,请稍后再试");
    }

    @ApiOperation(value = "用于验证用户的绑定邮箱")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "emailAddress", value = "邮箱地址", required = true, dataType = "String"),
    })
    @GetMapping("/VerifyEmail")
    public Object verifyEmail(String secret) {
        secret = secret.replace(' ', '+');
        try {
            String json = AESCrypt.decrypt(secret);
            Map data = JSON.parseObject(json);
            String userId = data.get("userId").toString();
            long generateDate = (long) data.get("generateDate");
            String emailAddress = data.get("emailAddress").toString();
            long nowTime = new Date().getTime();
            if ((nowTime - generateDate) / 1000 / 60 / 60 <= 48) {
                User user = userService.findFirstByUserId(userId);
                if (user != null) {
                    user.setEmailAddress(emailAddress);
                    userService.update(user);
                    logger.info(userId + "已绑定邮箱" + emailAddress);
                    return "<h1>😄邮箱绑定成功!</h1>";
                } else
                    return "<h1>😱查无此用户,验证失败!</h1>";
            } else {
                return "<h1>😥超出验证时间,验证失败,请重新请求绑定!</h1>";
            }
        } catch (Exception e) {
            return "<h1>🤒检测到非法的secret</h1>";
        }
    }

    //重设密码的接口, 如果使用单点登录的方式则请废弃此接口
    @ApiOperation(value = "用于所有用户修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "oldPassword", value = "原密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "newPassword", value = "重设的密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "confirmNewPassword", value = "确认重设的密码", required = true, dataType = "String"),
    })
    @PostMapping("/ResetPassword")
    public Object resetPassword(String oldPassword, String newPassword, String confirmNewPassword) {
        String userId = getUserIdFromRequest.getUserId(request);
        User user = userService.findFirstByUserId(userId);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            if (newPassword.equals(confirmNewPassword)) {
                newPassword = bCryptPasswordEncoder.encode(newPassword);
                user.setPassword(newPassword);
                user.setLastPasswordResetDate(new Date());
                userService.update(user);
                logger.info(userId + "已更改密码");
                return new ResponseMessage(0, "重设密码成功!");
            } else {
                return new ResponseMessage(-1, "两次输入的新密码不一致");
            }
        } else {
            return new ResponseMessage(-1, "原密码错误!");
        }
    }

}
