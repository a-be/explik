package com.hirath.explik.production;

public class SourceBuilder<B,A> {
    private Condition<B,A> condition;
    private boolean publish;

    public SourceBuilder(Condition<B,A> condition) {
        this.condition = condition;
        this.publish = true;
    }

    public static <B,A> SourceBuilder<B,A> when(Condition<B,A> condition){
        return new SourceBuilder<>(condition);
    }

    public SourceBuilder<B,A> disablePublish(){
        this.publish = false;
        return this;
    }

    public Source<B,A> then(Producer<B,A> producer){
        return new Source<>(condition, producer, publish);
    }
}
