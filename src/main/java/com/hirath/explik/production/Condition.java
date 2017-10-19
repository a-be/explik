package com.hirath.explik.production;

public interface Condition<B,A> {
    boolean match(Object bean, String tag, String description);
}
