package com.hirath.explik.production;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class ConditionsTest {
    private Conditions<Object,Object> condition;

    @Before
    public void setUp() throws Exception {
        condition = new Conditions<>();
    }

    @Test
    public void objectTypeWithStringObjectShouldReturnTrue(){
        Condition<String,Object> conditionOut = condition.objectType(String.class);

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match("",null,null)).isTrue();
    }

    @Test
    public void objectTypeWithNullShouldReturnFalse(){
        Condition<String,Object> conditionOut = condition.objectType(String.class);

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,null,null)).isFalse();
    }

    @Test
    public void objectTypeWithBooleanShouldReturnFalse(){
        Condition<String,Object> conditionOut = condition.objectType(String.class);

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(true,null,null)).isFalse();
    }

    @Test
    public void argumentTypeWithStringObjectShouldReturnTrue(){
        Condition<Object,String> conditionOut = condition.returnType(String.class);

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,null,null)).isTrue();
    }

    @Test
    public void returnTypeWithNullShouldReturnTrue(){
        Condition<Object,String> conditionOut = condition.returnType(String.class);

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,null,null)).isTrue();
    }

    @Test
    public void returnTypeWithBooleanShouldReturnTrue(){
        Condition<Object,String> conditionOut = condition.returnType(String.class);

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,null,null)).isTrue();
    }

    @Test
    public void objectTypeAndReturnTypeWithNullShouldReturnFalse(){
        Condition<String,String> conditionOut = condition.objectType(String.class).and().returnType(String.class);

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,null,null)).isFalse();
    }

    @Test
    public void objectTypeAndReturnTypeWith2StringShouldReturnTrue(){
        Condition<String,String> conditionOut = condition.objectType(String.class).and().returnType(String.class);

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match("",null,null)).isTrue();
    }

    @Test
    public void objectTypeAndReturnTypeWith1StringShouldReturnFalse(){
        Condition<String,String> conditionOut = condition.objectType(String.class).and().returnType(String.class);

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,null,null)).isFalse();
    }

    @Test
    public void descriptionMatchesWithNullShouldReturnFalse(){
        Condition<Object,Object> conditionOut = condition.descriptionMatches(".*");

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,null,null)).isFalse();
    }

    @Test
    public void descriptionMatchesWithMatchingStringShouldReturnTrue(){
        Condition<Object,Object> conditionOut = condition.descriptionMatches(".*");

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,null,"test")).isTrue();
    }

    @Test
    public void descriptionMatchesWithUnmatchingStringShouldReturnTrue(){
        Condition<Object,Object> conditionOut = condition.descriptionMatches("a");

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,null,"test")).isFalse();
    }

    @Test
    public void tagMatchesWithNullShouldReturnFalse(){
        Condition<Object,Object> conditionOut = condition.tagMatches(".*");

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,null,null)).isFalse();
    }

    @Test
    public void tagMatchesWithMatchingStringShouldReturnTrue(){
        Condition<Object,Object> conditionOut = condition.tagMatches(".*");

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,"test",null)).isTrue();
    }

    @Test
    public void tagMatchesWithUnmatchingStringShouldReturnTrue(){
        Condition<Object,Object> conditionOut = condition.tagMatches("a");

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,"test",null)).isFalse();
    }

    @Test
    public void objectSatisfiesShouldReturnPredicateResult(){
        Condition<Object,Object> conditionOut = condition.objectSatisfies((object) -> true);

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,null,null)).isTrue();
    }

    @Test
    public void tagMatchesAndDescriptionMatchesWithMatchingTagOnlyShouldReturnFalse(){
        Condition<Object,Object> conditionOut = condition.tagMatches("^.*tag.*$").and().descriptionMatches("^.*description.*$");

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,"my tag",null)).isFalse();
    }

    @Test
    public void tagMatchesAndDescriptionMatchesWithMatchingTagAndDescriptionShouldReturnTrue(){
        Condition<Object,Object> conditionOut = condition.tagMatches("^.*tag.*$").and().descriptionMatches("^.*description.*$");

        Assertions.assertThat(conditionOut).isNotNull();
        Assertions.assertThat(conditionOut.match(null,"my tag","my description")).isTrue();
    }
}