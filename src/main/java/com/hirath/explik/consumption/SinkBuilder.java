package com.hirath.explik.consumption;

public class SinkBuilder<B,A> {
    private Condition<B,A> condition;
    private boolean publish;

    public SinkBuilder(Condition<B, A> condition) {
        this.condition = condition;
        this.publish = true;
    }

    public static <B,A> SinkBuilder<B,A> when(Condition<B,A> condition){
        return new SinkBuilder<>(condition);
    }

    public SinkBuilder<B,A> disablePublish(){
        this.publish = false;
        return this;
    }

    public Sink<B,A> then(Consumer<B,A> consumer){
        return new Sink<>(condition, consumer, publish);
    }
}
