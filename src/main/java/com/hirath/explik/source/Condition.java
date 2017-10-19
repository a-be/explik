package com.hirath.explik.source;

public interface Condition<B,A> {
    boolean match(Object bean, String tag, String description);
}
