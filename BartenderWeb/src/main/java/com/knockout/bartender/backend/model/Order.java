package com.knockout.bartender.backend.model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

public class Order implements Comparable<Order>{
	private Customer customer;
	private Beverage order;
	private long idOrder;
	private String status;
	private Date createdDate;
	private Date startDate;
	private long timePreparing = 0;

	public static String NEWORDER ="New";
	public static String PROCESSINGORDER = "Preparing";
	public static String COMPLETEORDER = "Served";
	public static String ORDERREJECTED = "Reject";
	
	private static final AtomicLong counter = new AtomicLong(0);
	
	private static Logger log = Logger.getLogger(Order.class);
	
	public Order(int customerNumber, String order){
		log.debug("Creating order: customerNumber["+customerNumber+"] order["+order+"]");
		this.idOrder= counter.incrementAndGet();
		this.customer = new Customer(customerNumber);
		this.status = Order.NEWORDER;
		this.createdDate = new Date();
		if(order.equals(Beer.BEER)){
			this.order = new Beer();
		}else if(order.equals(Drink.DRINK)){
			this.order = new Drink();
		}else{
			idOrder = -1;
			log.debug("Unkwon Type of order");
		}
		log.debug("Order created: "+this.idOrder);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Beverage getOrder() {
		return order;
	}

	public void setOrder(Beverage order) {
		this.order = order;
	}

	public long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(long idOrder) {
		this.idOrder = idOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date date) {
		this.createdDate = date;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public long getTimePreparing() {
		return timePreparing;
	}

	public void setTimePreparing(long timePreparing) {
		this.timePreparing = timePreparing;
	}

	@Override
	public int compareTo(Order compareOrder) {
		long compareId = ((Order) compareOrder).getIdOrder();

		//descending order
		return (int) (compareId - this.idOrder);

	}
	
}
