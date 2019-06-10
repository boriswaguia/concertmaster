package com.github.boriswaguia.concertmaster.action.state;

import com.github.boriswaguia.concertmaster.action.api.ActionNode;
import com.github.boriswaguia.concertmaster.commons.AbstractId;
import com.github.boriswaguia.concertmaster.commons.ActionId;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class StateMachineBuilderTest {


    @Test
    public void test_state_machine_new_instance_return_empty_action_nodes() {
        // Given
        StateMachineBuilder stateMachineBuilder = new StateMachineBuilder();


        // When
        Collection<ActionNode> actionNodes =  stateMachineBuilder.getActionNodes();

        // Assert
        assertThat(actionNodes.size(), equalTo(0));
    }


    @Test
    public void test_state_machine_new_instance_return_null_action_node_builder() {
        // Given
        StateMachineBuilder stateMachineBuilder = new StateMachineBuilder();


        // When
        ActionNode.ActionNodeBuilder nodeBuilder = stateMachineBuilder.getActionNodeBuilder();

        // Assert
        assertThat(nodeBuilder, nullValue());
    }

    @Test
    public void test_state_machine_when_append_action_node_with_id() {
        // Given
        StateMachineBuilder stateMachineBuilder = new StateMachineBuilder();

        // When
        stateMachineBuilder.when(new ActionId("12345"));
        ActionNode.ActionNodeBuilder actionNode = stateMachineBuilder.getActionNodeBuilder();

        // Assert
        assertThat(actionNode.build().getId(), equalTo("12345"));
    }

    @Test
    public void test_state_machine_when_append_action_return_empty_action_nodes() {
        // Given
        StateMachineBuilder stateMachineBuilder = new StateMachineBuilder();

        // When
        stateMachineBuilder.when(new ActionId("12345"));

        // Assert
        assertThat(stateMachineBuilder.getActionNodes().size(), equalTo(0));
    }


    @Test
    public void test_state_machine_append_should_save_current_node_in_nodes_list() {
        // Given
        StateMachineBuilder stateMachineBuilder = new StateMachineBuilder();

        // When
        Collection<ActionNode> actionNodes = stateMachineBuilder
                .when(new ActionId("12345"))
                    .success(new AbstractId("12345"))
                    .exception(new AbstractId("12345"))
                    .append()
                .getActionNodes();

        // Assert
        assertThat(actionNodes.size(), equalTo(1));
    }


    @Test
    public void test_get_action_nodes_should_return_empty_nodes_if_no_when_method_called() {
        // Given
        StateMachineBuilder builder = StateMachine.builder();
        Collection<ActionNode> actionNodes = StateMachine.builder().getActionNodes();

        assertThat(actionNodes.size(), equalTo(0));
    }
    @Test
    public void test_add_action_node_should_return_exact_amounts() {

        StateMachine machine = StateMachine.builder()
                .when(new ActionId("abc2"))
                .success(new AbstractId("abc2"))
                .exception(new AbstractId("abc3"))
                .append()
                .when(new ActionId("action-1234"))
                .success(new AbstractId("action-1234"))
                .exception(new AbstractId("action-1234"))
                .append()
                .getMachine();

        assertThat(machine.getActionNodes().size(), equalTo(2));
    }

}
