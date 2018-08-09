package cn.edu.upc.eduroamcontrolsystembackend.model.radius;


import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jay on 2018/08/09
 */

@Entity
@Table(name = "radpostauth")
public class RadPostAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;
    private String pass;
    private String reply;
    private Timestamp authdate;

    public RadPostAuth() {
    }

    public RadPostAuth(String username, String pass, String reply, Timestamp authdate) {
        this.username = username;
        this.pass = pass;
        this.reply = reply;
        this.authdate = authdate;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Timestamp getAuthdate() {
        return authdate;
    }

    public void setAuthdate(Timestamp authdate) {
        this.authdate = authdate;
    }
}
