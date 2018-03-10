<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

    <head>

        <link rel="stylesheet" href="../css/bootstrap.min.css"/>         
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="../js/bootstrap.min.js"></script>  
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>     

    </head>



    <body>          

        <div class="container">

            <h2>Employees</h2>

            <!--Search Form -->

            <form action="/employee" method="get" id="seachEmployeeForm" role="form">

                <input type="hidden" id="searchAction" name="searchAction" value="searchByName">

                <div class="form-group col-xs-5">

                    <input type="text" name="employeeName" id="employeeName" class="form-control" required="true" placeholder="Type the Name or Last Name of the employee"/>                    

                </div>

                <button type="submit" class="btn btn-info">

                    <span class="glyphicon glyphicon-search"></span> Search

                </button>

                <br></br>

                <br></br>

            </form>

            

            <!--Employees List-->

            <form action="/employee" method="post" id="employeeForm" role="form" >              

               

                <c:choose>

                    <c:when test="${not empty employeeList}">

                        <table  class="table table-striped">

                            <thead>

                                <tr>

                                    <td>#</td>

                                    <td>Name</td>

                                    <td>Last name</td>

                                    <td>Birth date</td>

                                    <td>Role</td>

                                    <td>Department</td>

                                    <td>E-mail</td>                                 

                                </tr>

                            </thead>

                            <c:forEach var="employee" items="${employeeList}">

                                <c:set var="classSucess" value=""/>

                                <c:if test ="${idEmployee == employee.id}">                        	

                                    <c:set var="classSucess" value="info"/>

                                </c:if>

                                <tr class="${classSucess}">

                                    <td>${employee.id}</td>

                                    <td>${employee.name}</td>

                                    <td>${employee.lastName}</td>

                                    <td>${employee.birthDate}</td>

                                    <td>${employee.role}</td>

                                    <td>${employee.department}</td>

                                    <td>${employee.email}</td>   

                                   

                                </tr>

                            </c:forEach>               

                        </table>  

                    </c:when>                    

                    <c:otherwise>

                        <br>           

                        <div class="alert alert-info">

                            No people found matching your search criteria

                        </div>

                    </c:otherwise>

                </c:choose>                        

            </form>

           

        </div>

    </body>

</html>