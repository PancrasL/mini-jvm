package indi.pancras.jvm.rtda.symbolref;

import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.JClass;

/**
 * @author PancrasL
 */
public abstract class BaseSymbolRef {
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
    protected JClass clazz;

    public BaseSymbolRef(RuntimeConstantPool pool, String className) {
        this.pool = pool;
        this.className = className;
    }

    /**
     * 获取引用的类变量
     *
     * @return 解析后的类变量
     */
    public JClass resolvedClass() {
        if (clazz == null) {
            clazz = resolveClassRef();
        }
        return clazz;
    }

    private JClass resolveClassRef() {
        //如果类D通过符号引用N引用类C的话，要解析N，
        //先用D的类加载器加载C，然后检查D是否有权限访问C，如果没有,
        //则抛出IllegalAccessError异常
        JClass d = pool.getClazz();
        JClass c = d.getClassLoader().loadClass(className);
        if (!c.canBeAccessedBy(d)) {
            throw new IllegalAccessError();
        }
        return c;
    }
}
