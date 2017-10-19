package com.hirath.explik.consumption;

public interface Consumer<B,A> {
    void take(B bean, A argument, String tag, String description);
}
