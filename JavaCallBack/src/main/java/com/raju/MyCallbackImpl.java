package com.raju;

public class MyCallbackImpl implements MyCallback{
    @Override
    public void myFunction(String param) {
        System.out.println("Hello Dear "+param+"!!!");
    }
}
