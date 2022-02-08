package indi.pancras.jvm.testclass;

public class MultiarrayTest {
    public static void main(String[] args) {
        int[][][] arr = new int[2][3][4];
        arr[0][0][0] = 200;
        System.out.println(arr[0][0][0]);
        int[][] arr1 = new int[2][3];
        arr1[0][0] = 100;
        System.out.println(arr1[0][0]);
    }
}
