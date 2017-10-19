package com.hirath.explik.source;

import com.hirath.explik.mapping.NoSourceFoundException;

import java.util.Comparator;
import java.util.List;

public class SourceFinder<B> {
    private List<Source<?,?>> sources;
    private B bean;

    public SourceFinder(List<Source<?, ?>> sources, B bean) {
        this.sources = sources;
        this.bean = bean;
    }

    public <A> Source<B,A> find(String tag, String description){
        return sources.stream()
                      .filter(source -> source.match(bean, tag, description))
                      .map(source -> (Source<B,A>) source)
                      .sorted(Comparator.comparing(Source::getPriority))
                      .findFirst()
                      .orElseThrow(() -> new NoSourceFoundException("no source found"));
    }
}
