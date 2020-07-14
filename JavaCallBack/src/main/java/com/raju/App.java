package com.raju;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        TestClass callback = new TestClass();
        doWork(callback, "Raj ");
    }

    public static void doWork(TestClass myCallback, String param) {
        System.out.println("doing work ");
        myCallback.myFunction(param);
    }
}
