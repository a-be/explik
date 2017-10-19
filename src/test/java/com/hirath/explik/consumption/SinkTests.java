package com.hirath.explik.consumption;

import static org.mockito.Matchers.any;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.hirath.explik.TestDataGenerator;

@RunWith(MockitoJUnitRunner.class)
public class SinkTests {
    private Sink<Object,Object> sink;
    @Mock
    private Condition<Object,Object> condition;
    @Mock
    private Consumer<Object,Object> consumer;
    @Mock
    private Object bean;
    @Mock
    private Object argument;
    private String tag;
    private String description;

    @Before
    public void setUp() throws Exception {
        tag = TestDataGenerator.randomString();
        description = TestDataGenerator.randomString();
        Mockito.when(condition.match(any(), any(), any(), any())).thenReturn(true);
        sink = SinkBuilder.when(condition).disablePublish().then(consumer);
    }

    @Test
    public void matchWithNullObjectShouldReturnConditionResult() {
        matchShouldReturnConditionResult(null, argument, tag, description);
    }

    @Test
    public void matchWithNullArgumentShouldReturnConditionResult() {
        matchShouldReturnConditionResult(bean, null, tag, description);
    }

    @Test
    public void matchWithNullTagShouldReturnConditionResult() {
        matchShouldReturnConditionResult(bean, argument, null, description);
    }

    @Test
    public void matchWithNullDescriptionShouldReturnConditionResult() {
        matchShouldReturnConditionResult(bean, argument, tag, null);
    }

    @Test
    public void takeWithNullObjectShouldCallConsumer() {
        takeShouldCallConsumer(null, argument, tag, description);
    }

    @Test
    public void takeWithNullArgumentShouldCallConsumer() {
        takeShouldCallConsumer(bean, null, tag, description);
    }

    @Test
    public void takeWithNullTagShouldCallConsumer() {
        takeShouldCallConsumer(bean, argument, null, description);
    }

    @Test
    public void takeWithNullDescriptionShouldCallConsumer() {
        takeShouldCallConsumer(bean, argument, tag, null);
    }

    private void matchShouldReturnConditionResult(Object bean, Object argument, String tag, String description) {
        boolean result = sink.match(bean, argument, tag, description);

        Assertions.assertThat(result).isTrue();
        Mockito.verify(condition).match(bean, argument, tag, description);
    }

    private void takeShouldCallConsumer(Object bean, Object argument, String tag, String description) {
        sink.take(bean, argument, tag, description);

        Mockito.verify(consumer).take(bean, argument, tag, description);
    }
}