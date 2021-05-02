package me.devksh930.entity.compositeKey;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;

public class ChildId implements Serializable {
    private String parent; //Child.parent 매핑
    private String childId; //Child.childId 매핑

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildId childId1 = (ChildId) o;
        return parent.equals(childId1.parent) && childId.equals(childId1.childId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, childId);
    }
}
