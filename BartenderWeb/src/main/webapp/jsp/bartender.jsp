<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
    <head>
    	<!-- External scripts -->
        <link rel="stylesheet" href="../css/bootstrap.min.css"/>         
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="../js/bootstrap.min.js"></script>  
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>     
   	
   		<!-- WebApp Scripts  -->
   		<script type="text/javascript" src="js/bartender.js"></script>
   		
    </head>
    <body>          
        <div class="container">
            <h2>Bartender Web</h2>
            <!--Bartender Actions Form -->
             <div class="form-group col-xs-5">                    
                <p class="text-primary">Request a beverage</p>
             </div>
            <form action="/bartender" method="post" id="orderBartenderForm" role="form">
                <div class="form-group col-xs-5">
                	<label for="cust">Customer</label>
			        <input type="text" name="customerNumber" id="customerNumber" class="form-control" required="true" 
			                 placeholder="Type the Customer number"/>                    
			    </div>
				<div class="form-group">
				  <label for="sel1">Select a beverage</label>
				  <select class="form-control" id="beverage" name="beverage">
				    <option value="Drink">Drink</option>
				    <option value="Beer">Beer</option>
				  </select>
				</div>
                <div class="btn-group">
	                <button id="buttonBeverage" type="submit" class="btn btn-primary">
	                    Order it
	                </button>
                </div>
            </form>
            <br></br>
            <!--Actions List-->
            <form action="/bartender" method="post" id="bartenderForm" role="form" >              
                <div class="form-group col-xs-5">                    
                	<p class="text-primary">Bartender Work Line</p>
                </div>
                <div class="btn-group">
	                <button id="buttonRefresh" type="submit" class="btn btn-primary">
	                    Refresh
	                </button>
                </div>
                <br></br>
                <c:choose>
                    <c:when test="${not empty orderList}">
                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    <td># Order</td>
                                    <td>Customer</td>
                                    <td>Beverage</td>
                                    <td>Status</td>
                                     <td>Time Preparing</td> 
                                    <td>Date</td>   
                                                               
                                </tr>
                            </thead>
                            <c:forEach var="order" items="${orderList}">
                                <c:set var="classSucess" value=""/>
                                <c:if test ="${idOrder == order.idOrder}">                        	
                                    <c:set var="classSucess" value="info"/>
                                </c:if>
                                <tr class="${classSucess}">
                                    <td>${order.idOrder}</td>
                                    <td>${order.customer}</td>
                                    <td>${order.order}</td>
                                    <td>${order.status}</td>
                                    <td>${order.timePreparing}</td>  
                                    <td>${order.createdDate}</td> 
                                    
                                </tr>
                            </c:forEach>               
                        </table>  
                    </c:when>                    
                    <c:otherwise>
                        <br>           
                        <div class="alert alert-info">
                            No order has been ordered yet
                        </div>
                    </c:otherwise>
                </c:choose>                        
            </form>
           
        </div>
    </body>
</html>