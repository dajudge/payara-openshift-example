package com.dajudge.chatapp.transport;

/**
 * Created by dajudge on 21.02.2017.
 */
public class MessageTO {
    private String id;
    private String message;

    public MessageTO() {
    }

    public MessageTO(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
