package com.knockout.bartender.backend;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.knockout.bartender.backend.model.Beverage;
import com.knockout.bartender.backend.model.Order;


public class BartenderOrderList {
	static boolean listReady = false;
    private static final HashMap<Long,Order> orderList; 
    private static final ArrayBlockingQueue<Order> workingList; 
   
    private static Logger log = Logger.getLogger(BartenderOrderList.class);
    
    static {
    	orderList = new HashMap<Long,Order>();
    	workingList = new ArrayBlockingQueue<Order>(2);
    	listReady = true;
    }
    
    /**
     * Add an order to the working List (list of order waiting to be prepared)
     * @param newOrder
     * @return true if the order has been added
     */
    public static boolean addOrder(Order newOrder){
    	boolean resAdd= false;
    	//CASES
    	log.debug("WorkingList size: "+workingList.size());
    	switch(workingList.size()){
    	case 0:
    		log.debug("WorkingList.Task AddOrder case 0");
	    	//1. Empty <- Beer = Beer
    		//3. Empty <- Drink = Drink
    		resAdd = workingList.add(newOrder);
    		break;
    	case 1:
    		log.debug("WorkingList.Task AddOrder case 1");
    		//2. Beer <- Beer = Beer, Beer
    		if(workingList.peek().getOrder().equals(Beverage.BEER) && newOrder.equals(Beverage.BEER)){
    			resAdd =workingList.add(newOrder);
    		}else{
    			log.debug("WorkingList.Task AddOrder case Reject: WorkingList.size("+workingList.size()+")");
    			newOrder.setStatus(Order.ORDERREJECTED);
    			//4. Drink <- Drink = Drink (Reject)
    	    	//5. Beer <- Drink = Beer (Reject)
    	    	//6. Drink <- Beer = Drink (Reject)
    			resAdd = false;
    		break;
    		}
	    }
    	orderList.put(newOrder.getIdOrder(), newOrder);
    	log.debug("Order "+newOrder.getIdOrder()+" was added. OrderList Size: "+orderList.size());
    	return resAdd;
    }
    
    /**
     * Get the head of the working list (list of order waiting to be prepared)
     * @return the id of the order at the Head. -1 if the working list is empty
     */
    public static long getNextOrder(){
    	Order order = workingList.poll();
    	long id = -1;
    	if(order!= null){
    		log.debug("WorkingList updated. Preparing Order: "+order.getIdOrder()+" "+order.getOrder().toString());
    		id = order.getIdOrder();
    	}else{
    		log.debug("WorkingList empty.");
    	}
    	return id;
    }
    
    /**
     * Get the size of the working list (list of order waiting to be prepared)
     * @return the size
     */
    public static int numOrderToPrepare(){
    	return workingList.size();
    }
    
    /**
     * Get the order which Id is the param. Get the order from the OrderList
     * @param id long
     * @return Object order
     */
    public static Order getOrder(long id){
    	return orderList.get(id);
    }
    
    /**
     * Get a list from the Order List (all the orders)
     * @return
     */
    public static List<Order> getAllOrders(){  
    	List<Order> list = new ArrayList<Order>(orderList.values());
    	log.debug("Returning sorted list of "+list.size()+" orders");
    	Collections.sort(list);
    	return  list;
    }

}

