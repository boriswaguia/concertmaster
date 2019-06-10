package com.github.boriswaguia.concertmaster.action.state;

import com.github.boriswaguia.concertmaster.commons.AbstractId;
import com.github.boriswaguia.concertmaster.commons.ActionId;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class StateMachineSubCaseBuilderTest {


    @Test(expected = IllegalArgumentException.class)
    public void test_subcase_add_success_id_illegal_argument_exception() {
        // Given
        StateMachineBuilder builder = StateMachine.builder();
        builder.when(new ActionId("12345"));
        StateMachineSubCaseBuilder caseBuilder = new StateMachineSubCaseBuilder(builder);

        // When
        caseBuilder.success(null);

        // Then
        // expected:  IllegalArgumentException
    }

    @Test
    public void test_subcase_add_success_id_ok() {
        // Given
        StateMachineSubCaseBuilder caseBuilder = StateMachine.builder().when(new ActionId("12345"));

        // When
        StateMachineSubCaseBuilder result = caseBuilder.success(new AbstractId("12345"));

        // Then
        assertThat(result.getActionNode().build().getOnSuccessId(), equalTo("12345"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void test_subcase_add_exception_id_illegal_argument_exception() {
        // Given
        StateMachineBuilder builder = StateMachine.builder();
        builder.when(new ActionId("12345"));
        StateMachineSubCaseBuilder caseBuilder = new StateMachineSubCaseBuilder(builder);

        // When
        caseBuilder.exception(null);

        // Then
        // expected: IllegalArgumentException
    }

    @Test
    public void test_subcase_add_exception_id_ok() {
        // Given
        StateMachineSubCaseBuilder caseBuilder = StateMachine.builder().when(new ActionId("12345"));

        // When
        StateMachineSubCaseBuilder result = caseBuilder.exception(new AbstractId("12345"));

        // Then
        assertThat(result.getActionNode().build().getOnExceptionId(), equalTo("12345"));
    }

    @Test
    public void test_subcase_append_call_state_machine_flush() {
        // Given
        StateMachineBuilder spy = Mockito.spy(StateMachine.builder());
        StateMachineSubCaseBuilder subCaseBuilder = spy.when(new ActionId("12345"));
        subCaseBuilder.exception(new AbstractId("12345"));

        // When
        StateMachineBuilder append = subCaseBuilder.append();

        // Then
        verify(spy, times(1)).flush();
    }

}
