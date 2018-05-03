package cn.edu.upc.eduroamcontrolsystembackend.model;

import javax.persistence.*;

/**
 * 黑名单表
 *
 * @author jay
 * @date 2018/05/02
 */

@Entity
@Table(name = "black_list")
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int userId;

    public Blacklist() {
    }


    public Blacklist(int userId) {
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
