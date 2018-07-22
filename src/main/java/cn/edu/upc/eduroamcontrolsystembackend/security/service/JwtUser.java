package cn.edu.upc.eduroamcontrolsystembackend.security.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * This is description.
 *
 * @author jay
 * @date 2018/07/22
 */

public class JwtUser {

    public JwtUser(int id, int userId, String password, List<GrantedAuthority> grantedAuthorities) {
    }
}
