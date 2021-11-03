package indi.pancras.jvm;

/**
 * 测试文件，编译好的class文件位于resources/test/GaussTest.class，被InterpreterTest使用
 */
public class GaussTest {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
