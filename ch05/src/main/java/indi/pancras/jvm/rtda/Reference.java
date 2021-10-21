package indi.pancras.jvm.rtda;

import lombok.Getter;

/**
 * @author PancrasL
 */
@Getter
public class Reference {
    private final Object value;

    public Reference(Object value) {
        this.value = value;
    }
}
