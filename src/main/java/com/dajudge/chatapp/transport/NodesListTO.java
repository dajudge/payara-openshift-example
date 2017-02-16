package com.dajudge.chatapp.transport;

/**
 * Created by dajudge on 21.02.2017.
 */
public class NodesListTO {
    private String myNode;
    private String[] nodes;

    public NodesListTO() {
    }

    public NodesListTO(String myNode, String[] nodes) {
        this.myNode = myNode;
        this.nodes = nodes;
    }

    public void setMyNode(String myNode) {
        this.myNode = myNode;
    }

    public String getMyNode() {
        return myNode;
    }

    public String[] getNodes() {
        return nodes;
    }

    public void setNodes(String[] nodes) {
        this.nodes = nodes;
    }
}
