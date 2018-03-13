package com.knockout.bartender.backend.model;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

public class Beer implements Beverage {
	
	private long idBeer;

	private static Logger log = Logger.getLogger(Beer.class);
	private static final AtomicLong counter = new AtomicLong(0);
	
    public Beer(){
    	idBeer = counter.getAndIncrement();
    	log.debug("A Beer is requested");
    }


    @Override
    public String toString() {
        return BEER;
    }


	@Override
	public long getId() {
		return idBeer;
	}



    

}

