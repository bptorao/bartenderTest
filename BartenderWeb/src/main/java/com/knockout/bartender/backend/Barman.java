package com.knockout.bartender.backend;

import java.util.Date;

import org.apache.log4j.Logger;

import com.knockout.bartender.backend.model.Order;
import com.knockout.bartender.resources.Config;


public class Barman extends Thread {
	volatile static boolean stop = false;
	private static Logger log = Logger.getLogger(Barman.class);
	private long preparingTime = 0;
	private long idOrder;

	
	public Barman(){
		super("Barman");
		try{
			String preTime = Config.value("bartender", "timeToPreparing");	
			preparingTime = new Long(preTime).longValue();
		}catch(Exception ex){
			log.error("Error loading PreparingTime Configuration. Default 5s");
			preparingTime = 5000;
		}
		log.debug(" - Time to prepare Order has set to "+preparingTime+"ms");
		
	}
	
	@Override
	public void run(){
		log.debug("Barman: Taking customers orders. What I can get for you?");
		try{
			while(!stop){
				if(BartenderOrderList.numOrderToPrepare()>0){
						//One order to prepare: Beer or Drink
						startPreparingOrder();
						sleep(preparingTime);
						stopPreparingOrder();
				}
					
			}
		}catch(InterruptedException ie){
			log.error("ERROR: Barman cannot sleep properly"+ie.getMessage());
			ie.printStackTrace();
		}
		
	}
	
	/**
	 * Start to prepare the order. Set the status and register the time.
	 */
	private void startPreparingOrder(){
		idOrder = BartenderOrderList.getNextOrder();
		log.debug("Start Preparing Order: "+idOrder);
		BartenderOrderList.getOrder(idOrder).setStartDate(new Date());
		BartenderOrderList.getOrder(idOrder).setStatus(Order.PROCESSINGORDER);
	}
	
	/**
	 * Stop to prepare the order. Set the status and register the time.
	 */
	private void stopPreparingOrder(){
		BartenderOrderList.getOrder(idOrder).setTimePreparing( (new Date()).getTime() - BartenderOrderList.getOrder(idOrder).getStartDate().getTime());
		BartenderOrderList.getOrder(idOrder).setStatus(Order.COMPLETEORDER);
		log.debug("Stop Preparing Order: "+idOrder);
	}
}
