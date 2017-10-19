package com.hirath.explik.mapping;

import com.hirath.explik.consumption.Sink;
import com.hirath.explik.consumption.SinkFinder;
import com.hirath.explik.production.Source;
import com.hirath.explik.production.SourceFinder;

import java.util.List;
import java.util.function.BiConsumer;

public class SinglePipe<B> {
    private List<Source<?,?>> sources;
    private List<Sink<?,?>> sinks;
    private BiConsumer<String, Object> publisher;
    private B bean;

    public SinglePipe(List<Source<?, ?>> sources, List<Sink<?, ?>> sinks, B bean) {
        this.sources = sources;
        this.sinks = sinks;
        this.bean = bean;
    }

    public void feed(String tag, String keyDescription, String valueDescription){
        try {
            feed(tag, keyDescription, keyDescription, valueDescription);
        } catch (NoSourceFoundException e){
            try {
                feed(tag, keyDescription, valueDescription, keyDescription);
            } catch (NoSourceFoundException | NoSinkFoundException ee){
                //TODO create a sepecific exception
            }
        }
    }

    private <A> void feed(String tag, String keyDescription, String firstDescription, String secondDescription){
        Source<B,A> source = findSource(tag, firstDescription);
        A argument = source.produce(bean, tag, firstDescription);

        Sink<B,A> sink = findSink(argument, tag, secondDescription);
        sink.take(bean, argument, tag, secondDescription);

        if(source.isPublishable() && sink.isPublishable())
            publisher.accept(keyDescription, argument);

    }

    private <A> Source<B, A> findSource(String tag, String description){
        return new SourceFinder<>(sources, bean).find(tag, description);
    }

    private <A> Sink<B, A> findSink(A argument, String tag, String description){
        return new SinkFinder<>(sinks, bean, argument).find(tag, description);
    }

}
