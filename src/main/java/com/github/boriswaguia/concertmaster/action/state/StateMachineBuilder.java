package com.github.boriswaguia.concertmaster.action.state;

import com.github.boriswaguia.concertmaster.action.api.ActionNode;
import com.github.boriswaguia.concertmaster.commons.ActionId;

import java.util.LinkedHashSet;
import java.util.Set;

public class StateMachineBuilder {

    private Set<ActionNode> nodes = new LinkedHashSet<>();

    private ActionNode.ActionNodeBuilder builder = null;

    public StateMachineSubCaseBuilder when(ActionId actionId) {
        ActionNode.ActionNodeBuilder newBuilder = ActionNode.builder();
        newBuilder.id(actionId.toString());
        this.builder = newBuilder;
        return new StateMachineSubCaseBuilder(this);
    }

    public ActionNode.ActionNodeBuilder getActionNodeBuilder() {
        return this.builder;
    }

    public Set<ActionNode> getActionNodes() {
        return nodes;
    }

    public void flush() {
        if (this.builder != null) {
            this.nodes.add(this.builder.build());
            this.builder = null;
        }
    }

    public StateMachine getMachine() {
        return new StateMachine(this.nodes);
    }
}

