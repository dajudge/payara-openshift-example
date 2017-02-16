package com.dajudge.chatapp.business;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import javax.annotation.Resource;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.net.UnknownHostException;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Singleton
@Lock(LockType.READ)
public class ChatService {
    @Resource(mappedName = "payara/Hazelcast")
    private HazelcastInstance hazelcast;

    private String nodeId;

    public String[] getNodes() {
        return hazelcast.getCluster().getMembers()
                .stream()
                .map(it -> {
                    try {
                        return it.getUuid() + "@" + it.getAddress().getInetAddress().toString();
                    } catch (UnknownHostException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray(size -> new String[size]);
    }

    public String getThisNode() {
        return getNodeId();
    }

    public void addMessage(final String message) {
        final IMap<Object, Object> chatMap = getChatMap();
        final long msgId = hazelcast.getIdGenerator("chatMessage").newId();
        chatMap.set(msgId, message);
    }

    private IMap<Object, Object> getChatMap() {
        return hazelcast.getMap("chatMap");
    }

    public Map<String, String> getMessages() {
        return getChatMap().entrySet().stream()
                .collect(toMap(
                        it -> it.getKey().toString(),
                        it -> it.getValue().toString()
                ));
    }

    private String getNodeId() {
        if (nodeId == null) {
            nodeId = hazelcast.getCluster().getLocalMember().getUuid();
        }
        return nodeId;
    }
}
