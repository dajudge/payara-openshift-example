package com.dajudge.chatapp.transport;

import java.util.Collection;

/**
 * Created by dajudge on 21.02.2017.
 */
public class MessageListCTO {
    private String myNode;
    private MessageTO[] messages;

    public MessageListCTO() {
    }

    public MessageListCTO(final String myNode, final Collection<MessageTO> messages) {
        this.myNode = myNode;
        this.messages = messages.stream().toArray(size -> new MessageTO[size]);
    }

    public MessageTO[] getMessages() {
        return messages;
    }

    public void setMessages(final MessageTO[] messages) {
        this.messages = messages;
    }

    public String getMyNode() {
        return myNode;
    }

    public void setMyNode(final String myNode) {
        this.myNode = myNode;
    }
}
