package com.hirath.explik.destination;

import com.hirath.explik.Prioritized;
import com.hirath.explik.Publishable;

public class Destination<B,A> implements Condition<B,A>, Consumer<B,A>, Prioritized, Publishable {
    private Condition<B,A> condition;
    private Consumer<B,A> consumer;
    private boolean publishable;
    private int priority;

    public Destination(Condition<B, A> condition, Consumer<B, A> consumer, boolean publishable, int priority) {
        this.condition = condition;
        this.consumer = consumer;
        this.publishable = publishable;
        this.priority = priority;
    }

    @Override
    public boolean match(Object bean, Object argument, String tag, String description) {
        return this.condition.match(bean, argument, tag, description);
    }

    @Override
    public void take(B bean, A argument, String tag, String description) {
        this.consumer.take(bean, argument, tag, description);
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
