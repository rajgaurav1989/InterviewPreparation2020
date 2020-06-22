package com.raju.product.concreteimpl;

import com.raju.product.Computer;

public class Desktop extends Computer{

	private String gpu ;
	
	public Desktop(String gpu) {
		this.gpu = gpu;
	}
	
	@Override
	public String getComputer() {
		return "This is a desktop computer with gpu "+this.gpu;
	}

}
