package com.knockout.bartender.frontend;


import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.knockout.bartender.backend.BartenderService;
import com.knockout.bartender.backend.model.Order;



@WebServlet(
        name = "BartenderServlet",
        urlPatterns = {"/bartender"}
)

public class BartenderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int TOO_MUCH_REQUEST = 429;

	BartenderService bartenderService = new BartenderService();

    private static Logger log = Logger.getLogger(BartenderServlet.class);
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	log.debug("Method Get. Loading App");
        List<Order> result = bartenderService.getAllOrders();
        forwardListOrders(req, resp, result);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	log.debug("Method Post. Working App");
        String customerParam = req.getParameter("customerNumber");
        String beverage = req.getParameter("beverage");
        int customerNumber;
        boolean res = true;
        log.debug("Params requested: ");
        log.debug("	customerParam: "+customerParam);
        log.debug("	beverage: "+beverage);
        
        try{
        	customerNumber = new Integer(customerParam).intValue();
        }catch(Exception e){
        	customerNumber = -1;
        }
        log.debug("Params Processed. Customer:"+customerNumber+" Beverage:"+beverage);
        
        if (customerNumber!=-1 && beverage!=null){
        	log.debug("Receiving order");
            res = bartenderService.recivesOrder(customerNumber, beverage);
            
        	// respond with 200 code when ordered drink will be served	
            if(!res){
            	//respond with 429 code when order is not accepted at the moment
            	log.debug("Order is rejected. Return Too Many Requests");
            	resp.setStatus(TOO_MUCH_REQUEST);
            }else{
            	log.debug("Order received. Return OK");
            }
        }
        
    	 List<Order> result = bartenderService.getAllOrders();

    	 forwardListOrders(req, resp, result);

    }

    
    private void forwardListOrders(HttpServletRequest req, HttpServletResponse resp, List<Order> orderList) throws ServletException, IOException {
    	log.debug("Forward List Orders");
        String nextJSP = "/jsp/bartender.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);

        req.setAttribute("orderList", orderList);

        dispatcher.forward(req, resp);

    }   
    

}

