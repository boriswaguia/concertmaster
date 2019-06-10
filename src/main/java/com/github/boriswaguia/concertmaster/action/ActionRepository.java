package com.github.boriswaguia.concertmaster.action;

import com.github.boriswaguia.concertmaster.action.api.Action;

import java.util.*;
import java.util.stream.Collectors;

public class ActionRepository {
    private Map<String, Action> actionMap = new LinkedHashMap<>();

    public static ActionRepository init() {
        return new ActionRepository();
    }

    public Collection<Action> getActions() {
        return this.actionMap.values();
    }

    public ActionRepository add(Action action) {
        if(action == null) throw new IllegalArgumentException("Action argument should not be null");
        this.actionMap.put(action.getId(), action);
        return this;
    }

    public ActionRepository addAll(Collection<Action> actions) {
        Set<Action> copy = new LinkedHashSet<>(actions);
        Map<String, Action> result = copy.stream().filter(e -> e != null).collect(Collectors.toMap(x -> x.getId(), x -> x));
        this.actionMap.putAll(result);
        return this;
    }

    public Action getAction(String id) {
        return actionMap.get(id);
    }
}
