package com.github.boriswaguia.concertmaster.commons.internal;

import com.github.boriswaguia.concertmaster.action.api.Action;

import java.util.Objects;

public abstract  class EquatableAction implements Action {

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        Action action = (Action) obj;

        boolean result  = false;
        if (getId() != null && (getId().equals(action.getId()))) {
            result = true;
        }

        return result;
    }

    @Override
    public String toString() {
        return getId() + " - "+getDescription();
    }
}
