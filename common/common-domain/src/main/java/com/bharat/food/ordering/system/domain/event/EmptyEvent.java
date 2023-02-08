package com.bharat.food.ordering.system.domain.event;
/*
 * @author bharat.verma
 * @created Wednesday, 08 February 2023
 */

public final class EmptyEvent implements DomainEvent<Void> {

    public static final EmptyEvent INSTANCE = new EmptyEvent();

    public EmptyEvent() {

    }

    @Override
    public void fire() {

    }
}
