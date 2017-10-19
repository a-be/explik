package com.hirath.explik.destination;

public class DestinationBuilder<B,A> {
    private Condition<B,A> condition;
    private boolean publish;

    public DestinationBuilder(Condition<B, A> condition) {
        this.condition = condition;
        this.publish = true;
    }

    public static <B,A> DestinationBuilder<B,A> when(Condition<B,A> condition){
        return new DestinationBuilder<>(condition);
    }

    public DestinationBuilder<B,A> disablePublish(){
        this.publish = false;
        return this;
    }

    public Destination<B,A> then(Consumer<B,A> consumer){
        return new Destination<>(condition, consumer, publish);
    }
}
