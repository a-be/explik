package com.hirath.explik.consumption;

import com.hirath.explik.consumption.Sink;
import com.hirath.explik.mapping.NoSinkFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SinkFinder<B,A> {
    private List<Sink<?,?>> sinks;
    private B bean;
    private A argument;

    public SinkFinder(List<Sink<?, ?>> sinks, B bean, A argument) {
        this.sinks = sinks;
        this.bean = bean;
        this.argument = argument;
    }

    public Sink<B,A> find(String tag, String description){
        return sinks.stream()
                    .filter(source -> source.match(bean, argument, tag, description))
                    .map(sink -> (Sink<B,A>) sink)
                    .sorted(Comparator.comparing(Sink::getPriority))
                    .findFirst()
                    .orElseThrow(() -> new NoSinkFoundException("no sink found"));
    }
}
