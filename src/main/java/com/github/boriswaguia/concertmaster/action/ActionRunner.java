package com.github.boriswaguia.concertmaster.action;

import com.github.boriswaguia.concertmaster.action.api.ActionNode;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ActionRunner {

    private ActionRunner() {}

    public static ActionRunner instance() {
        return new ActionRunner();
    }

    public void run(Set<ActionNode> actionNodes, ActionRepository actionRepository) {
        Set<ActionNode> nodes = new LinkedHashSet<>(actionNodes);

        runAction(actionRepository, nodes.iterator().next(), nodes);

    }

    private void runAction(ActionRepository actionRepository, ActionNode currentNode, Set<ActionNode> nodes) {
        Map<String, ActionNode> nodesMap = nodes.stream().collect(Collectors.toMap(x -> x.getId(), x -> x));
        String id = currentNode.getId();
        String onSuccessId = currentNode.getOnSuccessId();
        String onExceptionId = currentNode.getOnExceptionId();

        Object context = new Object();
        try {
            actionRepository.getAction(id).run(context);

            if(nodesMap.containsKey(onSuccessId)) {
                runAction(actionRepository, nodesMap.get(onSuccessId), nodes );
            }
        } catch (Exception e) {
            if(nodesMap.containsKey(onExceptionId)) {
                actionRepository.getAction(onExceptionId).run(context);
            }
        }
    }
}
