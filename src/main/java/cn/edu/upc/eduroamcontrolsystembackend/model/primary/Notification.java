package cn.edu.upc.eduroamcontrolsystembackend.model.primary;

import javax.persistence.*;

/**
 * Created by jay on 2018/08/12
 */

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String comeFrom;
    private String sendTo;
    private String message;
    private boolean viewed;
    private boolean deleted;

    public Notification(String comeFrom, String sendTo, String message) {
        this.comeFrom = comeFrom;
        this.sendTo = sendTo;
        this.message = message;
        this.viewed = false;
        this.deleted = false;
    }

    public int getId() {
        return id;
    }

    public String getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
