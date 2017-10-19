package com.hirath.explik.source;

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
public class SourceTests {
    private Source<Object,Object> source;
    @Mock
    private Condition<Object,Object> condition;
    @Mock
    private Producer<Object,Object> producer;
    @Mock
    private Object bean;
    @Mock
    private Object result;
    private String tag;
    private String description;

    @Before
    public void setUp() throws Exception {
        tag = TestDataGenerator.randomString();
        description = TestDataGenerator.randomString();
        Mockito.when(condition.match(any(), any(), any())).thenReturn(true);
        Mockito.when(producer.produce(any(), any(), any())).thenReturn(result);
        source = SourceBuilder.when(condition).disablePublish().then(producer);
    }

    @Test
    public void matchWithNullObjectShouldReturnConditionResult() {
        matchShouldReturnConditionResult(null, tag, description);
    }

    @Test
    public void matchWithNullTagShouldReturnConditionResult() {
        matchShouldReturnConditionResult(bean, null, description);
    }

    @Test
    public void matchWithNullDescriptionShouldReturnConditionResult() {
        matchShouldReturnConditionResult(bean, tag, null);
    }

    @Test
    public void produceWithNullObjectShouldReturnProducerResult() {
        produceShouldCallProducer(null, tag, description);
    }

    @Test
    public void produceWithNullTagShouldReturnProducerResult() {
        produceShouldCallProducer(bean, null, description);
    }

    @Test
    public void produceWithNullDescriptionShouldReturnProducerResult() {
        produceShouldCallProducer(bean, tag, null);
    }

    private void matchShouldReturnConditionResult(Object bean, String tag, String description) {
        boolean result = source.match(bean, tag, description);

        Assertions.assertThat(result).isTrue();
        Mockito.verify(condition).match(bean, tag, description);
    }

    private void produceShouldCallProducer(Object bean, String tag, String description) {
        Object outResult = source.produce(bean, tag, description);

        Assertions.assertThat(outResult).isEqualTo(result);
        Mockito.verify(producer).produce(bean, tag, description);
    }
}