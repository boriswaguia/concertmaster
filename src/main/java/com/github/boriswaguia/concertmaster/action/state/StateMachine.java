package com.github.boriswaguia.concertmaster.action.state;

import com.github.boriswaguia.concertmaster.action.api.ActionNode;

import java.util.Set;

public class StateMachine {

    private Set<ActionNode> actionNodes;

    public StateMachine(Set<ActionNode> nodes) {
        this.actionNodes = nodes;
    }

    public static StateMachineBuilder builder() {
        return new StateMachineBuilder();
    }

    public Set<ActionNode> getActionNodes() {
        return this.actionNodes;
    }

}
