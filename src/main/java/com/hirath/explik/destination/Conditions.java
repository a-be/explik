package com.hirath.explik.destination;

import java.util.function.Predicate;

public class Conditions<B,A> {

    private Condition<B, A> condition;

    public Conditions() {
        condition = (bean, argument, tag, description) -> true;
    }

    public Conditions(Condition<B, A> condition) {
        this.condition = condition;
    }

    public <B> ChainingCondition<B,A> objectType(Class<B> type) {
        return (bean, argument, tag, description) -> condition.match(bean, argument, tag, description) && type.isInstance(bean);
    }

    public <A> ChainingCondition<B,A> argumentType(Class<A> type) {
        return (bean, argument, tag, description) -> condition.match(bean, argument, tag, description) && type.isInstance(argument);
    }

    public ChainingCondition<B,A> descriptionMatches(String regex) {
        return (bean, argument, tag, description) -> condition.match(bean, argument, tag, description) && description != null && description.matches(regex);
    }

    public ChainingCondition<B,A> tagMatches(String regex) {
        return (bean, argument, tag, description) -> condition.match(bean, argument, tag, description) && tag != null && tag.matches(regex);
    }


    public ChainingCondition<B,A> objectSatisfies(Predicate<B> objectMatcher) {
        return (bean, argument, tag, description) -> condition.match(bean, argument, tag, description) && objectMatcher.test((B) bean);
    }

    public ChainingCondition<B,A> argumentSatisfies(Predicate<A> objectMatcher) {
        return (bean, argument, tag, description) -> condition.match(bean, argument, tag, description) && objectMatcher.test((A) argument);
    }

    public <B,A> ChainingCondition<B,A> satisfy(Condition<B,A> aCondition) {
        return (bean, argument, tag, description) -> condition.match(bean, argument, tag, description) && aCondition.match(bean, argument, tag, description);
    }
}
