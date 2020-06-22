package com.raju;

import com.raju.factory.ComputerFactory;
import com.raju.factory.impl.AppleFactory;
import com.raju.factory.impl.HPFactory;

public class AbstractFactory {

	public static ComputerFactory getComputerFactory (String factoryType) {
		if (factoryType.equalsIgnoreCase("Apple")) {
			return new AppleFactory();
		}
		return new HPFactory();
	}
	
}
