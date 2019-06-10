package com.github.boriswaguia.concertmaster.action.state;

import com.github.boriswaguia.concertmaster.action.api.ActionNode;
import com.github.boriswaguia.concertmaster.commons.AbstractId;

public class StateMachineSubCaseBuilder {
    private final StateMachineBuilder machineBuilder;

    public StateMachineSubCaseBuilder(StateMachineBuilder machineBuilder) {

        if(machineBuilder.getActionNodeBuilder() == null) {
            String msg = "actionNodeBuilder is not initialised. Initialize properly by doing : machineBuilder.when(new ActionId('1234');";
            throw new IllegalStateException(msg);
        }

        this.machineBuilder = machineBuilder;
    }

    public StateMachineSubCaseBuilder success(AbstractId id) {
        if(id == null) throw new IllegalArgumentException("Id is required");
        ActionNode.ActionNodeBuilder nodeBuilder = machineBuilder.getActionNodeBuilder();
        nodeBuilder.onSuccessId(id.toString());
        return this;
    }


    public StateMachineSubCaseBuilder exception(AbstractId id) {
        if(id == null) throw new IllegalArgumentException("Id is required");
        ActionNode.ActionNodeBuilder nodeBuilder = machineBuilder.getActionNodeBuilder();
        nodeBuilder.onExceptionId(id.toString());
        return this;
    }


    public ActionNode.ActionNodeBuilder getActionNode() {
        return machineBuilder.getActionNodeBuilder();
    }

    public StateMachineBuilder append() {
        machineBuilder.flush();
        return machineBuilder;
    }
}
