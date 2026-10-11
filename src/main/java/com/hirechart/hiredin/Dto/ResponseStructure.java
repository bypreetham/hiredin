package com.hirechart.hiredin.Dto;

public class ResponseStructure<T> {
	int statuscode;
	String message;
	T data;
	public ResponseStructure(int statuscode, String message, T data) {
		super();
		this.statuscode = statuscode;
		this.message = message;
		this.data = data;
	}
	public ResponseStructure() {
		super();
	}
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	

}
