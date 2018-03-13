package com.knockout.bartender.backend;


import java.util.List;

import org.apache.log4j.Logger;

import com.knockout.bartender.backend.model.Order;


public class BartenderService {
	
	Barman barman;

	private static Logger log = Logger.getLogger(BartenderService.class);
   
    /**
     * Constructor
     */
    public BartenderService(){
    	log.debug("Starting Bartender Service: Hiring a Barman :) ");
    	barman = new Barman();
    	log.debug("Barman arriving to work");
    	barman.start();
    	log.debug("Barman ready to work. Barman id "+barman.getId());
    }
    
    /**
     * Receives an order 
     * @param customerNumber Integer 
     * @param beverage (Drink|Beer)
     * @return true if the order has been accepted, false if the order has been rejected.
     */
    public boolean recivesOrder(int customerNumber,String beverage){
    	log.debug("Order received: "+customerNumber+" "+beverage);
    	Order order = new Order(customerNumber,beverage);
    	return BartenderOrderList.addOrder(order);
    }
    
    /**
     * Get the list of order from the Order List (all the orders)
     * @return List <Order>
     */
    public List<Order> getAllOrders() {       
        return BartenderOrderList.getAllOrders();

    }
    
}

