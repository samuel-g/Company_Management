/**
 * 
 */
package company_management.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author samue
 *
 */
public class CompanyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8405457781559423830L;
	private String idcompany; 
	private String company_name;
	private String phone;
	private String email;
	private ArrayList<EmployeeBean> companyEmployees;
	
	public String getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(String idcompany) {
		this.idcompany = idcompany;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<EmployeeBean> getCompanyEmployees() {
		return companyEmployees;
	}
	public void setCompanyEmployees(ArrayList<EmployeeBean> companyEmployees) {
		this.companyEmployees = companyEmployees;
	}
		

}
