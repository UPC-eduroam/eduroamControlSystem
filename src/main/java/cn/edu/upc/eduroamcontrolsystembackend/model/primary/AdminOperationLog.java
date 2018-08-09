package cn.edu.upc.eduroamcontrolsystembackend.model.primary;

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

    private String adminId;
    private String operatingTime;
    private String level;
    private String operation;

    public AdminOperationLog() {
    }

    public AdminOperationLog(String adminName, String operatingTime, String level, String operation) {
        this.adminId = adminName;
        this.operatingTime = operatingTime;
        this.level = level;
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(String operatingTime) {
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
