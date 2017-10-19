package com.hirath.explik.destination;

public interface Consumer<B,A> {
    void take(B bean, A argument, String tag, String description);
}
