package indi.pancras.jvm.rtda;

import java.util.Objects;

import indi.pancras.jvm.rtda.heap.JObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 对象引用，应该是一个指针，但是由于Java不支持指针，因此此结构体进行模拟
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
        return target == reference.target;
    }

    @Override
    public int hashCode() {
        return Objects.hash(target);
    }
}
