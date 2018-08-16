package com.wkfs.calcEngineWebApp.model;

public class Operation {
	private String operation;
	private String operands;
	public String getOperation() {
		return operation;
	}
	
	public Operation(String operation, String operands) {
		super();
		this.operation = operation;
		this.operands = operands;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getOperands() {
		return operands;
	}
	public void setOperands(String operands) {
		this.operands = operands;
	}
	public String toString() {
		String toReturn=this.operation;
		this.operands=this.operands.substring(1, this.operands.length()-1);
		//this.operands=this.operands.substring(this.operands.length()-1, this.operands.length());
		String operandsString=this.operands.replaceAll(","," ");
		return toReturn=toReturn+" "+operandsString;
		
	}
}
