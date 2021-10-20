package indi.pancras.jvm.classpath;

/**
 * @author PancrasL
 */
public interface Entry {
    /**
     * 读取classFile文件，该文件以.class结尾
     *
     * @param classFile 类文件的路径，如java/lang/String.class
     * @return byte[], maybe null if the classfile not exist
     */
    byte[] readClass(String classFile);
}
