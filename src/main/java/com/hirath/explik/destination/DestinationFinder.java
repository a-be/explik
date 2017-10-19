package com.hirath.explik.destination;

import com.hirath.explik.mapping.NoSinkFoundException;

import java.util.Comparator;
import java.util.List;

public class DestinationFinder<B,A> {
    private List<Destination<?,?>> destinations;
    private B bean;
    private A argument;

    public DestinationFinder(List<Destination<?, ?>> destinations, B bean, A argument) {
        this.destinations = destinations;
        this.bean = bean;
        this.argument = argument;
    }

    public Destination<B,A> find(String tag, String description){
        return destinations.stream()
                           .filter(source -> source.match(bean, argument, tag, description))
                           .map(sink -> (Destination<B,A>) sink)
                           .sorted(Comparator.comparing(Destination::getPriority))
                           .findFirst()
                           .orElseThrow(() -> new NoSinkFoundException("no sink found"));
    }
}
