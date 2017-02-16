package com.dajudge.chatapp.rest;

import com.dajudge.chatapp.boundary.ChatFacade;
import com.dajudge.chatapp.transport.MessageListCTO;
import com.dajudge.chatapp.transport.NodesListTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/chat")
public class ChatResource {
    @Inject
    private ChatFacade chatFacade;

    @GET
    @Path("/nodes")
    @Produces("application/json")
    public NodesListTO getNodes() {
        return chatFacade.getNodes();
    }

    @GET
    @Path("/messages/post/{message}")
    public MessageListCTO postMessage(@PathParam("message") String message) {
        return chatFacade.addMessage(message);
    }

    @GET
    @Path("/messages")
    public MessageListCTO getMessages() {
        return chatFacade.getMessages();
    }
}
