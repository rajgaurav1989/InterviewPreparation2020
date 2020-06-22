package com.raju.factory.impl;


import com.raju.factory.ComputerFactory;
import com.raju.product.Computer;
import com.raju.product.concreteimpl.Desktop;
import com.raju.product.concreteimpl.Laptop;

public class AppleFactory extends ComputerFactory{

	@Override
	public Computer getComputer(String type ,String input) {
		if (type.equalsIgnoreCase("Laptop")){
			return new Laptop("Apple "+input);
		}
		return new Desktop("Apple "+input);
	}

}
