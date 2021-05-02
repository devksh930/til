package me.devksh930.entity.compositeKey;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChildId implements Serializable {
    private String parentId; //@MapsId("parentId")로 매핑
    @Column(name = "CHILD_ID")
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildId childId = (ChildId) o;
        return parentId.equals(childId.parentId) && id.equals(childId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, id);
    }
}
