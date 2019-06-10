package com.github.boriswaguia.concertmaster.action.state;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class StateMachineTest {

    @Test
    public void test_builder_should_return_default_builder() {
        StateMachineBuilder builder = StateMachine.builder();
        assertThat(builder, notNullValue());
    }

}
