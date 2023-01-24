package application;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable{

	// to pass a user created object as a parameter in RMI, it should be serializable
    private String username;
    private String msg;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
