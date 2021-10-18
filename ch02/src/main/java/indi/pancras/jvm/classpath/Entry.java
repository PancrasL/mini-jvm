package indi.pancras.jvm.classpath;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author PancrasL
 */
public interface Entry {
    byte[] readClass(String classFile) throws IOException;
}
