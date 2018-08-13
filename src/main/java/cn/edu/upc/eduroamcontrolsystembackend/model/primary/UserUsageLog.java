package cn.edu.upc.eduroamcontrolsystembackend.model.primary;

import javax.persistence.*;

/**
 * 所有用户操作日志记录
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
    private String operatingTime;
    private String operation;

    public UserUsageLog() {
    }

    public UserUsageLog(String userId, String operatingTime, String operation) {
        this.userId = userId;
        this.operatingTime = operatingTime;
        this.operation = operation;
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

    public String getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(String operatingTime) {
        this.operatingTime = operatingTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
