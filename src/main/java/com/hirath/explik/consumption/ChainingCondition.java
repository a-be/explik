package com.hirath.explik.consumption;

public interface ChainingCondition<B,A> extends Condition<B,A> {
    default Conditions<B,A> and(){
        return new Conditions<>(this);
    }
}
