package com.hirath.explik.mapping;

import com.hirath.explik.destination.Destination;
import com.hirath.explik.destination.DestinationFinder;
import com.hirath.explik.source.Source;
import com.hirath.explik.source.SourceFinder;

import java.util.List;
import java.util.Map;

public class Router {
    private Trafic trafic;
    private List<Source<?,?>> sources;
    private List<Destination<?,?>> destinations;

    public Router(List<Source<?, ?>> sources, List<Destination<?, ?>> destinations) {
        this.sources = sources;
        this.destinations = destinations;
    }

    public <B> void route(B bean, String tag, Map<String,String> descriptions){
        descriptions.forEach((keyDescription, valueDescription) ->
                route(bean, tag, keyDescription, valueDescription)
        );
    }

    public <B> void route(B bean, String tag, String keyDescription, String valueDescription){
        try {
            feed(bean, tag, keyDescription, keyDescription, valueDescription);
        } catch (NoSourceFoundException e){
            try {
                feed(bean, tag, keyDescription, valueDescription, keyDescription);
            } catch (NoSourceFoundException | NoSinkFoundException ee){
                //TODO create a sepecific exception
            }
        }
    }

    private <B,A> void feed(B bean, String tag, String keyDescription, String firstDescription, String secondDescription){
        Source<B,A> source = findSource(bean, tag, firstDescription);
        A argument = source.produce(bean, tag, firstDescription);

        Destination<B,A> destination = findSink(bean, argument, tag, secondDescription);
        destination.take(bean, argument, tag, secondDescription);

        if(source.isPublishable() && destination.isPublishable())
            trafic.introduce(keyDescription, argument);

    }

    private <B, A> Source<B, A> findSource(B bean, String tag, String description){
        return new SourceFinder<>(sources, bean).find(tag, description);
    }

    private <B,A> Destination<B, A> findSink(B bean, A argument, String tag, String description){
        return new DestinationFinder<>(destinations, bean, argument).find(tag, description);
    }
}
