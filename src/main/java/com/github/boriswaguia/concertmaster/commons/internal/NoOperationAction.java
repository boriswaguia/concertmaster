package com.github.boriswaguia.concertmaster.commons.internal;

public class NoOperationAction extends EquatableAction {

    @Override
    public String getId() {
        return "concert-master-no-operation-action-test";
    }

    @Override
    public String getDescription() {
        return "No operation action";
    }

    @Override
    public void run(Object state) { }
}
