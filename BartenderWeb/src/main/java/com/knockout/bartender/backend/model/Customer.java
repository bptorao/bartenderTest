package com.knockout.bartender.backend.model;

public class Customer {

	private int idCustomer;
	
	public Customer(int id){
		this.idCustomer = id;
	}
	
	
	@Override
	public String toString(){
		return idCustomer+"";
	}


	public int getIdCustomer() {
		return idCustomer;
	}


	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	
}
