package com.raju.exception;

public class ParkingLotException extends Exception {

	private String errorMsg ;
	private static final long serialVersionUID = 1L;
	
	
	public ParkingLotException(String msg) {
		super();
		this.errorMsg = msg;
	}
	
}
