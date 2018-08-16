package cn.edu.upc.eduroamcontrolsystembackend.model.primary;

import cn.edu.upc.eduroamcontrolsystembackend.util.MyDateFormat;

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

    private String sender;
    private String receiver;
    private String message;
    private String createTime;
    private boolean viewed;
    private boolean receiverDeleted;
    private boolean senderDeleted;

    public Notification() {
    }

    public Notification(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.createTime = new MyDateFormat().formattedDate();
        this.viewed = false;
        this.receiverDeleted = false;
        this.senderDeleted = false;
    }

    public int getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateTime() {
        return createTime;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public boolean isReceiverDeleted() {
        return receiverDeleted;
    }

    public void setReceiverDeleted(boolean receiverDeleted) {
        this.receiverDeleted = receiverDeleted;
    }

    public boolean isSenderDeleted() {
        return senderDeleted;
    }

    public void setSenderDeleted(boolean senderDeleted) {
        this.senderDeleted = senderDeleted;
    }
}
