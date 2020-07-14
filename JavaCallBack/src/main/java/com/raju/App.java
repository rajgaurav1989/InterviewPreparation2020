package com.raju;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        TestClass callback = new TestClass();
        doWork(callback, "My Message");
    }

    public static void doWork(TestClass myCallback, String msg) {
        System.out.println("doing work " + msg);
        myCallback.myFunction("Raj");
    }
}
