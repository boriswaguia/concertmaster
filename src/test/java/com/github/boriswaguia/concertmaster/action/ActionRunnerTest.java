package com.github.boriswaguia.concertmaster.action;

import com.github.boriswaguia.concertmaster.action.impl.CreateUserActionImpl;
import com.github.boriswaguia.concertmaster.action.api.Action;
import com.github.boriswaguia.concertmaster.action.api.ActionNode;
import com.github.boriswaguia.concertmaster.action.impl.SendEmailToUserActionImpl;
import com.github.boriswaguia.concertmaster.action.state.StateMachine;
import com.github.boriswaguia.concertmaster.commons.AbstractId;
import com.github.boriswaguia.concertmaster.commons.ActionId;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ActionRunnerTest {

    @Test
    public void test_run_configured_actions() {
        // Given
        Action createUserActionAsSpy = Mockito.spy(new CreateUserActionImpl());
        Action sendEmailToUserActionAsSpy = spy(new SendEmailToUserActionImpl());

        // @formatter:off
        Set<ActionNode> actionNodes = StateMachine.builder()
                .when(new ActionId("create-user-action-impl"))
                    .success(new AbstractId("send-email-to-user-action-impl"))
                    .append()
                .when(new ActionId("send-email-to-user-action-impl"))
                    .append()
                .getMachine()
                .getActionNodes();
        // @formatter:on

        ActionRepository actionRepository = ActionRepository.init().add(createUserActionAsSpy).add(sendEmailToUserActionAsSpy);

        // When
        ActionRunner.instance().run(actionNodes, actionRepository);

        // Then
        verify(createUserActionAsSpy, times(1)).run(any());
        verify(sendEmailToUserActionAsSpy, times(1)).run(any());
    }


}
