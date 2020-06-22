package com.raju.product.concreteimpl;

import com.raju.product.Computer;

public class Laptop extends Computer {

	private String ram;
	
	public Laptop(String ram) {
		this.ram = ram;
	}
	
	@Override
	public String getComputer() {
		return "This is a laptop computer with ram "+this.ram;
	}

	
	

}
