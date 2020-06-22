package com.raju.product;

public abstract class Computer {
	
	public abstract String getComputer() ;
	
	@Override
	public String toString() {
		return this.getComputer();
	}
	
}
