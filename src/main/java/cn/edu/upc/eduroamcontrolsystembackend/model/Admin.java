package cn.edu.upc.eduroamcontrolsystembackend.model;

import javax.persistence.*;

/**
 * 管理员表
 *
 * @author jay
 * @date 2018/05/02
 */

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int adminId;

    public Admin() {
    }

    public Admin(int adminId) {
        this.adminId = adminId;
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
}
