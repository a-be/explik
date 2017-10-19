package com.hirath.explik.destination;

public interface Condition<B,A> {
    boolean match(Object bean, Object argument, String tag, String description);
}
