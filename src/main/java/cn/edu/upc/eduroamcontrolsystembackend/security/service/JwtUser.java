package cn.edu.upc.eduroamcontrolsystembackend.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * This is description.
 *
 * @author jay
 * @date 2018/07/22
 */

public class JwtUser implements UserDetails {
    private int id;
    private String userId;
    private String password;
    private final Date lastPasswordResetDate;
    private String emailAddress;
    private final Collection<? extends GrantedAuthority> authorities;


    public JwtUser(int id, String userId, String password, Date lastPasswordResetDate, String emailAddress, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.emailAddress = emailAddress;
        this.authorities = authorities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
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
