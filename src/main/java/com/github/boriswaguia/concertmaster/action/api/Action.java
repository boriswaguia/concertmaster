package com.github.boriswaguia.concertmaster.action.api;

public interface Action {
    String getId();
    String getDescription();
    void run(Object state);
}
