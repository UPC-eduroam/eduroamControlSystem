package cn.edu.upc.eduroamcontrolsystembackend.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 管理员操作记录表
 *
 * @author jay
 * @date 2018/05/02
 */

@Entity
@Table(name = "admin_operation_log")
public class AdminOperationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int adminId;
    private Date operatingTime;
    private String level;
    private String operation;

    public AdminOperationLog() {
    }

    public AdminOperationLog(int adminId, Date operatingTime, String level, String operation) {
        this.adminId = adminId;
        this.operatingTime = operatingTime;
        this.level = level;
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public Date getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(Date operatingTime) {
        this.operatingTime = operatingTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
