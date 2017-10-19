package com.hirath.explik.production;

import java.util.function.Predicate;

public class Conditions<B,A> {

    private Condition<B,A> condition;

    public Conditions() {
        condition = (bean, tag, description) -> true;
    }

    public Conditions(Condition<B,A> condition) {
        this.condition = condition;
    }

    public <B,A> ChainingCondition<B,A> objectType(Class<B> type) {
        return (bean, tag, description) -> condition.match(bean, tag, description) && type.isInstance(bean);
    }

    public <A> ChainingCondition<B,A> returnType(Class<A> type) {
        return (bean, tag, description) -> condition.match(bean, tag, description);
    }

    public ChainingCondition<B,A> descriptionMatches(String regex) {
        return (bean, tag, description) -> condition.match(bean, tag, description) && description != null && description.matches(regex);
    }

    public ChainingCondition<B,A> tagMatches(String regex) {
        return (bean, tag, description) -> condition.match(bean, tag, description) && tag != null && tag.matches(regex);
    }

    public ChainingCondition<B,A> objectSatisfies(Predicate<B> objectMatcher) {
        return (bean, tag, description) -> condition.match(bean, tag, description) && objectMatcher.test((B) bean);
    }

    public <B,A> ChainingCondition<B,A> satisfy(Condition<B,A> aCondition) {
        return (bean, tag, description) -> condition.match(bean, tag, description) && aCondition.match(bean, tag, description);
    }
}
