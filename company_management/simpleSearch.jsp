<%@ page import = "java.util.ArrayList" %>
<%@ page import = "company_management.bean.CompanyBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
String search_name = "";
ArrayList<CompanyBean> companiesList = new ArrayList<CompanyBean>();
if(request.getAttribute("COMPANIES") != null){
	companiesList = (ArrayList<CompanyBean>) request.getAttribute("COMPANIES");
}
if(request!=null && request.getAttribute("SEARCH")!=null){
	search_name = request.getAttribute("SEARCH") + "";
}
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Search for a Company</title>
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
                         
                                 <li class="nav-item active">
                                    <a href="#" class="nav-link">Companies List</a>
                                </li>
                                             
                            </ul>                        
                        </div>
                        
                    </nav>  

                </div>                                  
            </div>            
        </div>
     
 
                 <h3 class="tm-gold-text tm-form-title">Company Search</h3>
                 <form name="companyForm" action="/company_management/CompanyManagementServlet"
                 method="post" class="tm-contact-form">
                 	<div class="form-group">
                 		<input type="text" id="search_name" name="search_name" value="<%=search_name%>"
                 		class="form-control" placeholder="Company Name" maxlength="45" />
           			</div>
                 		<input name="whatsend" value="search" type="hidden">
                 		<button type="submit" class="tom-btn" value="Search"> Search </button>	
           		</form>
                 		
               	
                 
                 
                 <%if (request.getAttribute("COMPANIES")!=null){ %>

				
           
				<table class="table table-bordered">
			 		<thead class="thead-light">
			   	 		<tr>
					   		<th scope="col">Company ID</th>
		      				<th scope="col">Company Name</th>
			   	 		</tr>
	   	 			</thead>
	   	 		
	   	 		<% 
				for (CompanyBean company:companiesList){
				String idcompany = company.getIdcompany();
				String company_name = company.getCompany_name();
				%>
			
	   	 		<tbody>
	   	 			<tr>
	   	 				<th><%= idcompany %></th>
	   	 				<th><%= company_name %></th>
	   	 			</tr>
	   	 		</tbody>
	   	 		<%
				}
				%>
	   	 		</table>
	   	 		<%
				}
				%>

               <!-- load JS files -->
        <script src="js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="https://www.atlasestateagents.co.uk/javascript/tether.min.js"></script> <!-- Tether for Bootstrap, http://stackoverflow.com/questions/34567939/how-to-fix-the-error-error-bootstrap-tooltips-require-tether-http-github-h --> 
        <script src="js/bootstrap.min.js"></script>   
  </body>
</html>




