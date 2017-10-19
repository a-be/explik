package com.hirath.explik.source;

public interface ChainingCondition<B,A> extends Condition<B,A> {
    default Conditions<B,A> and(){
        return new Conditions<>(this);
    }
}
