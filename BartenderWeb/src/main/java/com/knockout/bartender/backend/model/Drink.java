package com.knockout.bartender.backend.model;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

public class Drink implements Beverage {

	private long idDrink;
	
	private static Logger log = Logger.getLogger(Drink.class);
	private static final AtomicLong counter = new AtomicLong(0);

    public Drink(){
    	idDrink = counter.getAndIncrement();
    	log.debug("A Drink is requested");
    }



    @Override
    public String toString() {
        return DRINK;
    }

	@Override
	public long getId() {
		return idDrink;
	}



    

}

