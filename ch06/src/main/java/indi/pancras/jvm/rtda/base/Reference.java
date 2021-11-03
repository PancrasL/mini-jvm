package indi.pancras.jvm.rtda.base;

import lombok.Getter;

@Getter
public class Reference {
    private final Object value;

    public Reference(Object value) {
        this.value = value;
    }
}
