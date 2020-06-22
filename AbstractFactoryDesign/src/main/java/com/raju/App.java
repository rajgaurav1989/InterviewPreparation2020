package com.raju;

import com.raju.factory.ComputerFactory;
import com.raju.product.Computer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ComputerFactory computerFactory = AbstractFactory.getComputerFactory("Apple");
    	Computer computer = computerFactory.getComputer("Laptop", "500 GB");
    	System.out.println(computer.toString());
    }
}
