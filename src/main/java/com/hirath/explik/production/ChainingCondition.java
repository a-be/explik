package com.hirath.explik.production;

public interface ChainingCondition<B,A> extends Condition<B,A> {
    default Conditions<B,A> and(){
        return new Conditions<>(this);
    }
}
