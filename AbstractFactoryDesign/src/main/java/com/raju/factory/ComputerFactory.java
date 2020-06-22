package com.raju.factory;

import com.raju.product.Computer;

public abstract class ComputerFactory {
	
	public abstract Computer getComputer(String type ,String input);
	

}
