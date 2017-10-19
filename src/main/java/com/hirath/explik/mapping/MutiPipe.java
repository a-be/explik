package com.hirath.explik.mapping;

import com.hirath.explik.consumption.Sink;
import com.hirath.explik.production.Source;

import java.util.List;
import java.util.Map;

public class MutiPipe {
    private List<Source<?,?>> sources;
    private List<Sink<?,?>> sinks;

    public MutiPipe(List<Source<?, ?>> sources, List<Sink<?, ?>> sinks) {
        this.sources = sources;
        this.sinks = sinks;
    }

    public <B> void map(B bean, String tag, Map<String,String> descriptions){
        SinglePipe<B> singlePipe = new SinglePipe<>(sources, sinks, bean);
        descriptions.forEach((keyDescription, valueDescription) ->
            singlePipe.feed(tag, keyDescription, valueDescription)
        );
    }
}
