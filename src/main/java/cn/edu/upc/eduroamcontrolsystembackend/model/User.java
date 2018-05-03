package cn.edu.upc.eduroamcontrolsystembackend.model;

import javax.persistence.*;

/**
 * 用户表
 *
 * @author jay
 * @date 2018/05/02
 */

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int userId;

    public User() {
    }

    public User(int userId) {
        this.userId = userId;
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

}
