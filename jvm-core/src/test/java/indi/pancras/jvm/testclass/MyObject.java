package indi.pancras.jvm.testclass;

public class MyObject {
    public static int staticVar;
    public int instanceVar;

    public static void main(String[] args) {
        // ldc
        int x = 32768;
        // new
        MyObject myObj = new MyObject();
        // putstatic
        MyObject.staticVar = x;
        // getstatic
        x = MyObject.staticVar;
        // putfield
        myObj.instanceVar = x;
        // getfield
        x = myObj.instanceVar;
        Object obj = myObj;
        // instanceof
        if (obj instanceof MyObject) {
            // checkcase
            myObj = (MyObject) obj;
        }
    }
}
