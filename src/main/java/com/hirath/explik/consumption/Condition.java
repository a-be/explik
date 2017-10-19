package com.hirath.explik.consumption;

public interface Condition<B,A> {
    boolean match(Object bean, Object argument, String tag, String description);
}
