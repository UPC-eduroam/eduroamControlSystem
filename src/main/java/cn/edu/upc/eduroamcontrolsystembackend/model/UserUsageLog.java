package cn.edu.upc.eduroamcontrolsystembackend.model;

import javax.persistence.*;
import java.util.Date;

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

    private int userId;
    private Date loginTime;
    private String orgDomain;

    public UserUsageLog() {
    }

    public UserUsageLog(int userId, Date loginTime, String orgDomain) {
        this.userId = userId;
        this.loginTime = loginTime;
        this.orgDomain = orgDomain;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getOrgDomain() {
        return orgDomain;
    }

    public void setOrgDomain(String orgDomain) {
        this.orgDomain = orgDomain;
    }
}
