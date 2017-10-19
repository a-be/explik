package com.hirath.explik.production;

public interface Producer<B,A> {
    A produce(B bean, String tag, String description);
}
