package cn.edu.upc.eduroamcontrolsystembackend.model;

import javax.persistence.*;

/**
 * 用户在校外使用eduroam的使用情况记录表
 *
 * @author jay
 * @date 2018/05/02
 */

@Entity
@Table(name = "user_usage_log")
public class UserUsageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String userId;
    private String loginTime;
    private String orgDomain;

    public UserUsageLog() {
    }

    public UserUsageLog(String userId, String loginTime, String orgDomain) {
        this.userId = userId;
        this.loginTime = loginTime;
        this.orgDomain = orgDomain;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getOrgDomain() {
        return orgDomain;
    }

    public void setOrgDomain(String orgDomain) {
        this.orgDomain = orgDomain;
    }
}
