package ru.job4j.caraccident.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriggerTest {

    @Test
    public void whenTriggerInit() {
        assertThat(new Trigger().someLogic(), is(1));
    }
}
