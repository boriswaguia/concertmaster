package com.github.boriswaguia.concertmaster.action.impl;

import com.github.boriswaguia.concertmaster.action.api.Action;

public class CreateUserActionImpl implements Action {
    @Override
    public String getId() {
        return "create-user-action-impl";
    }

    @Override
    public String getDescription() {
        return "Create a user and store in the database";
    }

    @Override
    public void run(Object state) {
        System.out.println("CreateUserActionImpl");
    }
}
