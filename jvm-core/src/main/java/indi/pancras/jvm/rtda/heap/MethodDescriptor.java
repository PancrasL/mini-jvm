package indi.pancras.jvm.rtda.heap;

import lombok.Getter;

@Getter
public class MethodDescriptor {
    private String[] paramTypes;
    private String returnType;

    public MethodDescriptor(String descriptor) {
        // TODO
    }
}
