package com.dajudge.chatapp.boundary;

import com.dajudge.chatapp.business.ChatService;
import com.dajudge.chatapp.transport.MessageListCTO;
import com.dajudge.chatapp.transport.MessageTO;
import com.dajudge.chatapp.transport.NodesListTO;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Singleton
@Lock(LockType.READ)
public class ChatFacade {
    @Inject
    private ChatService chatService;

    public NodesListTO getNodes() {
        return new NodesListTO(chatService.getThisNode(), chatService.getNodes());
    }

    public MessageListCTO addMessage(final String message) {
        chatService.addMessage(message);
        return getMessages();
    }

    public MessageListCTO getMessages() {
        Set<MessageTO> messages = chatService.getMessages().entrySet().stream()
                .map(it -> new MessageTO(it.getKey(), it.getValue()))
                .collect(toSet());
        return new MessageListCTO(chatService.getThisNode(), messages);
    }
}
