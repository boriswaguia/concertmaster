package com.github.boriswaguia.concertmaster.action.impl;

import com.github.boriswaguia.concertmaster.action.api.Action;

public class SendEmailToUserActionImpl implements Action {
    @Override
    public String getId() {
        return "send-email-to-user-action-impl";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void run(Object state) {

    }
}
