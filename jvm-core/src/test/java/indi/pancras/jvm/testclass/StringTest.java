package indi.pancras.jvm.testclass;

public class StringTest {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        System.out.println("你好，中国");
        String s1 = "abc1";
        String s2 = "abc1";
        System.out.println(s1 == s2); // true
        int x = 1;
        String s3 = "abc" + x;
        System.out.println(s1 == s3); // false
        s3 = s3.intern();
        System.out.println(s1 == s3); // true
    }
}
