package indi.pancras.jvm.rtda.heap;

import indi.pancras.jvm.classfile.field.FieldInfo;
import lombok.Getter;

@Getter
public class Field {
    private JClass clazz;

    private short accessFlags;
    private String name;
    private String descriptor;

    public Field(JClass clazz, FieldInfo info) {
        this.clazz = clazz;
        this.accessFlags = info.getAccessFlags();
        this.name = info.getName();
        this.descriptor = info.getDescriptor();
    }
}
