package com.hirath.explik.source;

import com.hirath.explik.Prioritized;
import com.hirath.explik.Publishable;

public class Source<B,A> implements Condition<B,A>, Producer<B,A>, Prioritized, Publishable {
    private Condition<B,A> condition;
    private Producer<B,A> producer;
    private boolean publishable;
    private int priority;

    public Source(Condition<B,A> condition, Producer<B,A> producer, boolean publishable, int priority) {
        this.condition = condition;
        this.producer = producer;
        this.publishable = publishable;
        this.priority = priority;
    }

    @Override
    public boolean match(Object bean, String tag, String description) {
        return this.condition.match(bean, tag, description);
    }

    @Override
    public A produce(B bean, String tag, String description) {
        return this.producer.produce(bean, tag, description);
    }

    @Override
    public boolean isPublishable() {
        return publishable;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
