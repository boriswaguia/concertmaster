package com.github.boriswaguia.concertmaster.commons.internal;

import com.github.boriswaguia.concertmaster.action.api.Action;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EquatableActionTest {

    @Test
    public void test_equals_action_null_param_return_false() {
        // Given
        Action noop = new NoOperationAction();
        Action action2 = null;

        // Then
        assertThat(noop.equals(action2), is(false));
    }


    @Test
    public void test_equals_action_reference_return_true() {
        // Given
        Action noop1 = new NoOperationAction();
        Action noop2 = noop1;

        // Then
        assertThat(noop1.equals(noop2), is(true));
    }

    @Test
    public void test_equals_action_value_return_true() {
        // Given
        Action noop1 = new NoOperationAction();
        Action noop2 = new NoOperationAction();

        // Then
        assertThat(noop1.equals(noop2), is(true));
    }

    @Test
    public void test_equals_action_value_return_false() {
        // Given
        Action noop1 = new NoOperationAction();
        Action noop2 = new EquatableAction() {
            @Override
            public String getId() {
                return null;
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void run(Object state) {

            }
        };

        // Then
        assertThat(noop1.equals(noop2), is(false));
    }


    @Test
    public void test_equals_action_value_different_id_return_false() {
        // Given
        Action noop1 = new NoOperationAction();
        Action noop2 = new EquatableAction() {
            @Override
            public String getId() {
                return UUID.randomUUID().toString();
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void run(Object state) {

            }
        };

        // Then
        assertThat(noop1.equals(noop2), is(false));
    }



    @Test
    public void test_equals_action_value_same_id_return_true() {
        // Given
        Action noop1 = new NoOperationAction();
        Action noop2 = new EquatableAction() {
            @Override
            public String getId() {
                return "concert-master-no-operation-action-test";
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void run(Object state) {

            }
        };

        // Then
        assertThat(noop1.equals(noop2), is(true));
    }


    @Test(expected = ClassCastException.class)
    public void test_equals_action_value_different_type_throw_cast_exception() {
        // Given
        Action noop1 = new NoOperationAction();
        Object unknowType = new Object();

        // When
        noop1.equals(unknowType);

        // Then
        // expected: ClassCastException
    }

}
