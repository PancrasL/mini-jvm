package indi.pancras.jvm.rtda.heap.symbolref;

import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;

public abstract class SymbolRef {
    /**
     * 符号引用所在的运行时常量池指针
     */
    protected RuntimeConstantPool pool;
    /**
     * 类的完全限定名
     */
    protected String className;
    /**
     * 解析后的类结构体指针
     */
    // TODO
    protected JClass jClass;

    public SymbolRef(RuntimeConstantPool pool, String className) {
        this.pool = pool;
        this.className = className;
    }
}
