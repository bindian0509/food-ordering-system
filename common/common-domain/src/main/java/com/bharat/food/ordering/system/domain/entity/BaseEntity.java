package com.bharat.food.ordering.system.domain.entity;
/*
 * @author bharat.verma
 * @created Tuesday, 09 August 2022
 */

import java.util.Objects;

public abstract class BaseEntity<ID> {
    private ID id;


    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return id.equals(that.id);
    }
}
