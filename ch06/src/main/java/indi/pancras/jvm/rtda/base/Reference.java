package indi.pancras.jvm.rtda.base;

import java.util.Objects;

import indi.pancras.jvm.rtda.heap.JObject;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
public class Reference {
    private final JObject target;

    public Reference(JObject target) {
        this.target = target;
    }

    public boolean targetIsNull() {
        return target == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reference reference = (Reference) o;
        return Objects.equals(target, reference.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target);
    }
}
