package com.raju;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        MyCallback callback = new MyCallbackImpl();
        doWork(callback, "My Message");
    }

    public static void doWork(MyCallback myCallback, String msg) {
        System.out.println("doing work " + msg);
        myCallback.myFunction("Raj");
    }
}
