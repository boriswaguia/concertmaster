package com.github.boriswaguia.concertmaster.commons;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class AbstractIdTest {

    @Test
    public void test_abstract_id_should_preserve_string_value() {

        // Given
        String value  = "12344556";

        // When
        AbstractId abstractId = new AbstractId("12344556");

        // Then
        MatcherAssert.assertThat(abstractId.toString(), Matchers.equalTo(value));
    }
}
