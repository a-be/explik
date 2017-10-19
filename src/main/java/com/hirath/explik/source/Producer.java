package com.hirath.explik.source;

public interface Producer<B,A> {
    A produce(B bean, String tag, String description);
}
