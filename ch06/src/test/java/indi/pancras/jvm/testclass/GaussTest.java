package indi.pancras.jvm.testclass;

/**
 * 测试文件，被InterpreterTest使用
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
