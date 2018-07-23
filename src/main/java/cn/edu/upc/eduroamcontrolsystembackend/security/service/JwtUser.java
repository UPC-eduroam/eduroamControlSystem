package cn.edu.upc.eduroamcontrolsystembackend.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Collection;
import java.util.List;

/**
 * This is description.
 *
 * @author jay
 * @date 2018/07/22
 */

public class JwtUser implements UserDetails {
    private int id;
    private int userId;
    private String password;
    private final Date LastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;


    public JwtUser(int id, int userId, String password, Date LastPasswordResetDate, List<GrantedAuthority> authorities) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.LastPasswordResetDate = LastPasswordResetDate;
        this.authorities = authorities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastPasswordResetDate() {
        return LastPasswordResetDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return String.valueOf(userId);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
