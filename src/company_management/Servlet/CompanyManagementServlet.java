package company_management.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company_management.bean.CompanyBean;
import company_management.bean.EmployeeBean;
import company_management.db.SaveMySQL;

/**
 * Servlet implementation class CompanyManagementServlet
 */
@WebServlet("/CompanyManagementServlet")
public class CompanyManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CompanyManagementServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	} 

	//servlet is called via direct link (GET...?whatsend=abc) not via the submit form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String whatsend = request.getParameter("whatsend");
		System.out.println("whatsend GET:: " +whatsend);
		
		if(whatsend.equals("employee")) {
		//FOUND MY ERROR:	request.getSession().removeAttribute("EMPLOYEE");
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formEmployee.jsp");
			rd.forward(request, response);
			
		}else if(whatsend.equals("employeeInsert")) {
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formEmployee.jsp");
			rd.forward(request, response); 
		}
		else if(whatsend.equals("search")) {
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/simpleSearch.jsp");
			rd.forward(request, response); 
		}
		else if(whatsend.equals("saveCompany")) {
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/listCompany.jsp");
			rd.forward(request, response); 
		}
		else if(whatsend.equals("company")) {
		//FOUND MY ERROR:	request.getSession().removeAttribute("COMPANY");
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formCompany.jsp");
			rd.forward(request, response); 
		}
		else if(whatsend.equals("homepage")) {
			request.getSession().removeAttribute("EMPLOYEE");
			request.getSession().removeAttribute("COMPANY");
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/hello.jsp");
			rd.forward(request, response); 
		}
		/*else if(whatsend.equalsIgnoreCase("saveCompany")) {
			CompanyBean company = new CompanyBean();
			company = (CompanyBean) request.getSession().getAttribute("COMPANY");
			SaveMySQL saveCompany = new SaveMySQL();
			try {
				saveCompany.insertCompany(company);
			} catch (SQLException e) {
				System.out.println("ERROR: " + e.getErrorCode() + ":" + e.getMessage());
				e.printStackTrace();
			}
		}*/
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String whatsend = request.getParameter("whatsend");
		System.out.println("whatsend POST:: " + whatsend);
		CompanyBean company = new CompanyBean();
		
		if(whatsend.equals("company")) {
			String idcompany = request.getParameter("idcompany"); //getting data from jsp page
			System.out.println("id company:: " + idcompany); 
			String company_name = request.getParameter("company_name");
			System.out.println("company_name:: " + company_name);
			String phone = request.getParameter("phone");
			System.out.println("phone:: " + phone);
			
			String email = request.getParameter("email");
			System.out.println("email:: " + email);

			ArrayList<EmployeeBean> companyEmployees = new ArrayList<EmployeeBean>();
			
			company.setIdcompany(idcompany); //save data into an object
			company.setCompany_name(company_name);
			company.setPhone(phone);
			company.setEmail(email);
			company.setCompanyEmployees(companyEmployees);
			
			request.getSession().removeAttribute("COMPANY"); //remove any old object
			request.getSession().setAttribute("COMPANY", company);	//create a new object
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formCompany.jsp");
			rd.forward(request, response); //Forwards a request from a servlet to another resource (servlet, JSP file, or HTML file) on the server.
			
			
		} else if (whatsend.equals("employee")) {
			
			String idemployee = request.getParameter("idemployee");
			System.out.println("id employee:: " + idemployee);
			String name = request.getParameter("name");
			System.out.println("name:: " + name);
			String surname = request.getParameter("surname");
			System.out.println("surname:: " + surname);
			String badge = request.getParameter("badge");
			System.out.println("badge:: " + badge);
			String fk_company = request.getParameter("FK_company");
			System.out.println("FK_company:: " + fk_company);
			
			//create employee bean from jsp page
			EmployeeBean employee = new EmployeeBean();
			
			employee.setIdemployee(idemployee);
			employee.setSurname(surname);
			employee.setBadge(badge);
			employee.setName(name);
			employee.setFk_company(fk_company);
			
			ServletContext sc = request.getSession().getServletContext();
			request.removeAttribute("EMPLOYEE");
			request.setAttribute("EMPLOYEE", employee);
			System.out.println("\n \n DISPATCHING TO formEmployee.jsp");
			RequestDispatcher rd = sc.getRequestDispatcher("/formEmployee.jsp");
			rd.forward(request, response);	
			
			
			//Add employee to company
			if(request.getSession()!=null && request.getSession().getAttribute("COMPANY")!=null) {
				company = (CompanyBean) request.getSession().getAttribute("COMPANY");
				ArrayList<EmployeeBean> companyEmployees = company.getCompanyEmployees(); //grab emp from company
				System.out.println("\t Adding Employee");
				companyEmployees.add(employee);
				company.setCompanyEmployees(companyEmployees);
				request.getSession().removeAttribute("COMPANY");
				request.getSession().setAttribute("COMPANY", company);	
			}
			if(request.getSession()!=null && request.getSession().getAttribute("COMPANY")!=null) {
			System.out.println("About to print");
			//PRINT EMPLOYEES
			company = (CompanyBean) request.getSession().getAttribute("COMPANY");
			ArrayList<EmployeeBean> companyEmployeesList = company.getCompanyEmployees();
			for(EmployeeBean c1 : companyEmployeesList) {
				System.out.println("Employee surname :: " + c1.getSurname() + "Employee name :: " + c1.getName());
			}
			}	
			
		}
		else if(whatsend.equalsIgnoreCase("saveCompany")) {
			company = (CompanyBean) request.getSession().getAttribute("COMPANY");
			SaveMySQL saveCompany = new SaveMySQL();
			boolean error=false;
			try {
				System.out.println("Is company null? " + company);
				System.out.println("Calling insert Company now: ");
				saveCompany.insertCompany(company);
			} catch (SQLException e){
				System.out.println("ERROR: " + e.getErrorCode() + ":" + e.getMessage());
				error = true;
				e.printStackTrace();
			}
			//Dispatch to the companies list JSP page
			ServletContext sc = request.getSession().getServletContext();
			request.removeAttribute("COMPANIES");
			if(error) {
				RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);	
			}
			else {
				ArrayList<CompanyBean> companyInDB = new ArrayList<CompanyBean>();
				try {
					companyInDB = saveCompany.searchCompanies();
					
				} catch (SQLException e) {
					System.out.println("ERROR: " + e.getErrorCode() + ":" + e.getMessage());
					e.printStackTrace();
				}
				request.setAttribute("COMPANIES", companyInDB);
				RequestDispatcher rd = sc.getRequestDispatcher("/listCompany.jsp");
				rd.forward(request, response);	
			}
			
			
		}
		else if(whatsend.equalsIgnoreCase("search")) {
			System.out.println("TESTING \n TESTING 2");
			String search_name = request.getParameter("search_name");
			System.out.println("search_name :: " + search_name);
			
		//	CompanyBean company = new CompanyBean();
		//	company = (CompanyBean) request.getSession().getAttribute("COMPANY");
			SaveMySQL searchCompany = new SaveMySQL();
			ArrayList<CompanyBean> companyInDB = new ArrayList<CompanyBean>();
			try {
				companyInDB = searchCompany.searchCompanies(search_name);
			} catch (SQLException e){
				System.out.println("ERROR: " + e.getErrorCode() + ":" + e.getMessage());
				e.printStackTrace();
			}
			//Dispatch to the companies list JSP page
			ServletContext sc = request.getSession().getServletContext();
			
			request.setAttribute("COMPANIES", companyInDB);
			request.setAttribute("SEARCH", search_name);
			RequestDispatcher rd = sc.getRequestDispatcher("/simpleSearch.jsp");
			rd.forward(request, response);	
			
			
		}
		}
	}

