package com.github.boriswaguia.concertmaster.action;

import com.github.boriswaguia.concertmaster.action.api.Action;
import com.github.boriswaguia.concertmaster.commons.internal.EquatableAction;
import com.github.boriswaguia.concertmaster.commons.internal.NoOperationAction;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ActionRepositoryTest {

    /* test init */
    @Test
    public void test_init_empty_action_repository_not_null() {
        // Given
        ActionRepository actionRepository = ActionRepository.init();

        // Then
        assertThat(actionRepository, notNullValue());
    }

    @Test
    public void test_init_empty_action_repository_not_null_list() {
        // Given
        ActionRepository actionRepository = ActionRepository.init();

        // Then
        assertThat(actionRepository.getActions(), notNullValue());
    }


    @Test
    public void test_init_empty_action_repository_is_empty() {
        // Given
        ActionRepository actionRepository = ActionRepository.init();

        // Then
        assertThat(actionRepository.getActions().size(), equalTo(0));
    }

    /* Test add operation */

    @Test(expected = IllegalArgumentException.class)
    public void test_add_null_action_throw_illegal_argument() {
        // Given
        ActionRepository repository = ActionRepository.init();

        // When
        repository.add(null);

        // Then
        // expected: IllegalArgumentException
    }

    @Test
    public void test_add_action_return_non_null_repository() {
        // Given
        ActionRepository repository = ActionRepository.init();

        // When
        ActionRepository repository2 = repository.add(new NoOperationAction());

        // Then
        assertThat(repository2, notNullValue());
    }

    @Test
    public void test_add_action_return_add_element_in_actions_list() {
        // Given
        ActionRepository repository = ActionRepository.init();

        // When
        ActionRepository repository2 = repository.add(new NoOperationAction());

        // Then
        assertThat(repository2.getActions(), contains(new NoOperationAction()));
    }

    @Test
    public void test_add_all_should_add_elements_in_actions_list_size() {
        // Given
        ActionRepository repository = ActionRepository.init();
        Action noop = new NoOperationAction();
        Action dump2 = new EquatableAction() {
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

        // When
        Collection<Action> actions = repository.addAll(Arrays.asList(noop, null, dump2, null, noop)).getActions();

        // Then
        assertThat( actions.size(), equalTo(2));
     }



    @Test
    public void test_add_all_should_add_elements_in_actions_list_contains() {
        // Given
        ActionRepository repository = ActionRepository.init();
        Action noop = new NoOperationAction();
        Action dump2 = new EquatableAction() {
            private String id = UUID.randomUUID().toString();
            @Override
            public String getId() {
                return id;
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void run(Object state) {
            }
        };

        // When
        Collection<Action> actions = repository.addAll(Arrays.asList(noop, null, dump2, null, noop)).getActions();

        // Then
        assertThat(actions, hasItems(noop, dump2));
    }

}
