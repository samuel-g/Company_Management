<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="company_management.bean.EmployeeBean" %>
<%@ page import="company_management.bean.CompanyBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- java scriptlet -->
<% 
	String idemployee = "";
String name = "";
String surname = "";
String badge = "";
String fk_company = "";
	
	//fields from session if actually present
  EmployeeBean employee = new EmployeeBean();
	CompanyBean company = new CompanyBean();
	boolean employeeFilled = false;


 //DON'T UNDERSTAND THIS IF STATEMENT, WHY DO WE NEED IT? TO INSERT ANOTHER EMPLOYEE...TO CHECK IF EMPLOYEE IS FILLED
if(request!=null && request.getAttribute("EMPLOYEE")!=null){ //Once you send an employee, then quickly want add another 
  employee=(EmployeeBean) request.getAttribute("EMPLOYEE");
  idemployee = employee.getIdemployee();
  name = employee.getName();
  surname = employee.getSurname();
  badge = employee.getBadge();
  employeeFilled = true;
}
  if(request.getSession()!=null && request.getSession().getAttribute("COMPANY")!=null){
  company=(CompanyBean) request.getSession().getAttribute("COMPANY");
  fk_company = company.getIdcompany()+"";
}

  
%>


<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Employee Form</title>
<!--
Classic Template
http://www.templatemo.com/tm-488-classic
-->
    <!-- load stylesheets -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400">  <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" href="css/templatemo-style.css">                                   <!-- Templatemo style -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
          <![endif]-->
</head>

<!-- nav menu -->
<body>
 <div class="tm-header">
            <div class="container-fluid">
                <div class="tm-header-inner">
                    <a href="#" class="navbar-brand tm-site-name">My Web Application</a>
                    
                    <!-- navbar -->
                    <nav class="navbar tm-main-nav">

                        <button class="navbar-toggler hidden-md-up" type="button" data-toggle="collapse" data-target="#tmNavbar">
                            &#9776;
                        </button>
                        
                        <div class="collapse navbar-toggleable-sm" id="tmNavbar">
                            <ul class="nav navbar-nav">
                                <li class="nav-item">
                                    <a href="/company_management/CompanyManagementServlet?whatsend=homepage" class="nav-link">Home</a>
                                </li>
                                <li class="nav-item">
                                    <a href="/company_management/CompanyManagementServlet?whatsend=company" class="nav-link">Company</a>
                                </li>
                                  <li class="nav-item active">
                                    <a href="#" class="nav-link">Employee</a>
                                </li>
                                <% if(employeeFilled){ %>
                                <li class="nav-item ">
              									<a href="/company_management/CompanyManagementServlet?whatsend=employee" class ="nav-
              									link">Insert another Employee</a>
              									</li>
              								<%} %>                  
                            </ul>                        
                        </div>
                        
                    </nav>  

                </div>                                  
            </div>            
        </div>
        
    <section class="tm-section">
   <div class="container-fluid">
       <div class="row">
           <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6 col-xl-6">

           <section>
                 <h3 class="tm-gold-text tm-form-title">Employee Form</h3>
                 <p class="tm-form-description">...</p> 

                 <form name="employeeForm" action="/company_management/CompanyManagementServlet"
                  method="post" class="tm-contact-form">
                  		<% if (employeeFilled){ %>
                  			<%=idemployee%>
                  		<%} else{ %>
                  	                       
                      <div class="form-group">
                         <input type="text" id="idemployee" name="idemployee" value="<%=idemployee%>" 
                         class="form-control" placeholder="Employee ID (*)"  maxlength=16 required/>
                     </div>
                     <%} %>

                     <div class="form-group">
                         <input type="text" id="name" name="name" value="<%=name%>" 
                         class="form-control" placeholder="Name (*)"  maxlength=16 required/>
                     </div>
                     
                     <div class="form-group">
                         <input type="text" id="surname" name="surname" value="<%=surname%>"
                          class="form-control" placeholder="Lastname (*)"  required/>
                     </div>
                     
                      <div class="form-group">
                         <input type="text" id="badge" name="badge" value="<%=badge%>"
                          class="form-control" placeholder="Company Badge (*)"  required/>
                     </div>
                     
             
                     <input type="hidden" id="fK_company" name="fK_company" value="<%=fk_company%>">

                     <input type="hidden" id="whatsend" name="whatsend" value="employee">


                     <button type="submit" class="tm-btn" value="Insert Company"> Insert Employee</button>
                   </form>
                     
             </section>
          </div>           		
          </div>
        </div>
     </section>
             
             
               <!-- load JS files -->
        <script src="js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="https://www.atlasestateagents.co.uk/javascript/tether.min.js"></script> <!-- Tether for Bootstrap, http://stackoverflow.com/questions/34567939/how-to-fix-the-error-error-bootstrap-tooltips-require-tether-http-github-h --> 
        <script src="js/bootstrap.min.js"></script>   
  </body>
</html>