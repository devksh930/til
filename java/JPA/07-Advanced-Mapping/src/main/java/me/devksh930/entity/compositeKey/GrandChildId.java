package me.devksh930.entity.compositeKey;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GrandChildId implements Serializable {
    private ChildId childId;

    @Column(name = "GRANDCHILD_ID")
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrandChildId that = (GrandChildId) o;
        return childId.equals(that.childId) && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(childId, id);
    }
}
