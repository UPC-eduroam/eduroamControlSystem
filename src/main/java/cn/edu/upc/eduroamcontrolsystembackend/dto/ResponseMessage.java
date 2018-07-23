package cn.edu.upc.eduroamcontrolsystembackend.dto;

/**
 * 返回给前端的消息对象,
 * 状态 0 为成功
 * 状态 -1 为错误
 *
 * @author jay
 * @date 2018/07/22
 */

public class ResponseMessage {
    private int state;
    private String message;

    public ResponseMessage(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
