package com.raju.models;

public class Location {
	private int rowNumber ;
	private int columnNumber ;
	
	public Location(int rowNumber,int columnNumber) {
		this.rowNumber = rowNumber ;
		this.columnNumber = columnNumber ;
	}
	
	public Location() {
	}
	
	public int getRowNumber() {
		return rowNumber;
	}
	
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	public int getColumnNumber() {
		return columnNumber;
	}
	
	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	@Override
	public String toString() {
		return "Location [rowNumber=" + rowNumber + ", columnNumber=" + columnNumber + "]";
	}	
	
	
	
}
