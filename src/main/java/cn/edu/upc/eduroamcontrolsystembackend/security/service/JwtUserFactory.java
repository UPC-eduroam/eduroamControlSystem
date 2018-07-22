package cn.edu.upc.eduroamcontrolsystembackend.security.service;

import cn.edu.upc.eduroamcontrolsystembackend.model.Authority;
import cn.edu.upc.eduroamcontrolsystembackend.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建JwtUser的工厂类
 *
 * @author jay
 * @date 2018/07/22
 */

public class JwtUserFactory {

    public static JwtUser createJwtUser(User user) {
        return new JwtUser(
                user.getId(),
                user.getUserId(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getAuthorities())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityType().name()))
                .collect(Collectors.toList());
    }
}
