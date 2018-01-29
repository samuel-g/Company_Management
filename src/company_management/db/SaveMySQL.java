package company_management.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import company_management.bean.CompanyBean;
import company_management.bean.EmployeeBean;

public class SaveMySQL {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/company_management";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "5500";
	
	
	public void insertCompany(CompanyBean company) throws SQLException {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			//sql script to insert company
			String insertCompany = "INSERT INTO COMPANY "
					+ "(idcompany, company_name, phone, email, date_ins)";
			
			insertCompany += " VALUES ('"
					+ company.getIdcompany() + "','"
					+ company.getCompany_name() + "','"
					+ company.getPhone() + "','"
					+ company.getEmail() + "',"
					+ " SYSDATE())";
			System.out.println("FAILING HERE2:");
			System.out.println("INSERT QUERY: " + insertCompany);
			stmt.executeUpdate(insertCompany);
			
			//sql script to insert employees
			ArrayList<EmployeeBean> employees = company.getCompanyEmployees();
			for(EmployeeBean employee:employees) {
				String insertEmployee = "INSERT INTO EMPLOYEE"
						+ "(idemployee, surname, name, badge, FK_company, date_ins)";
				insertEmployee +=  "VALUES ('" 
						+ employee.getIdemployee() + "','" 
						+ employee.getSurname() + "','" 
						+ employee.getName() + "','" 
						+ employee.getBadge() + "','" 
						+ employee.getFk_company() + "'," 
						+ " SYSDATE())";
					
				System.out.println("INSERT QUERY: " + insertEmployee);
				stmt.executeUpdate(insertEmployee);
				
			}
			conn.commit();
		} catch(SQLException sqle) {
			if(conn != null) conn.rollback();
			System.out.println("INSERT ERROR: Transaction is being rolled back");
			throw new SQLException(sqle.getErrorCode()+":" + sqle.getMessage());
			//if a transaction goes bad, it will be rolledback w/ error
			//and return to servlet with a throw
		}
		catch(Exception err) {
			if(conn != null) conn.rollback(); //back to servlet?
			System.out.println("GENERIC ERROR: Transaction is being rolledback");
			throw new SQLException(err.getMessage());
		}		
		finally {
		if (stmt != null) stmt.close();
		if(conn != null) conn.close();
	}

}
	
	
	//Search method
	public ArrayList<CompanyBean> searchCompanies() throws SQLException, IOException{
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = getDBConnection();
			stmt = conn.createStatement();
			String searchCompany = "SELECT * from COMPANY ";
			System.out.println("QUERY: " + searchCompany);
			ResultSet companyList = stmt.executeQuery(searchCompany);
			ArrayList<CompanyBean> companyListInDB = new ArrayList<CompanyBean>();
			while(companyList.next()) {
				CompanyBean company = new CompanyBean();
				String idcompany = companyList.getString("idcompany");
				String company_name = companyList.getString("company_name");
				company.setCompany_name(company_name);
				company.setIdcompany(idcompany);
				companyListInDB.add(company);
			}
			return companyListInDB;
	} catch (SQLException sqle) {
		throw new SQLException(sqle.getErrorCode() + ":" + sqle.getMessage());
	} catch (Exception err) {
		throw new SQLException(err.getMessage());
	} finally {
		if (stmt != null ) stmt.close();
		if (conn != null ) conn.close();
	}
}

	public ArrayList<CompanyBean> searchCompanies(String search_name) throws SQLException, IOException{
		
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = getDBConnection();
			stmt = conn.createStatement();
			String searchCompany = "SELECT * from COMPANY WHERE company_name like '%" + 
					search_name + "%'";
			System.out.println("QUERY: " + searchCompany);
			ResultSet companyList = stmt.executeQuery(searchCompany);
			System.out.println("companyList " + companyList);
			ArrayList<CompanyBean> companyListInDB = new ArrayList<CompanyBean>();
			while(companyList.next()) {
				CompanyBean company = new CompanyBean();
				String idcompany = companyList.getString("idcompany");
				String company_name = companyList.getString("company_name");
				company.setCompany_name(company_name);
				company.setIdcompany(idcompany);
				companyListInDB.add(company);
			}
			return companyListInDB;
	} catch (SQLException sqle) {
		throw new SQLException(sqle.getErrorCode() + ":" + sqle.getMessage());
	} catch (Exception err) {
		throw new SQLException(err.getMessage());
	} finally {
		if (stmt != null ) stmt.close();
		if (conn != null ) conn.close();
	}
}

	
	private static Connection getDBConnection() throws Exception {
		System.out.println("--- MySQL JDBC Connect ----");
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: MySQL JDBC Driver not found!!");
			throw new Exception (e.getMessage());
		} 
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			System.out.println("SQL Connection to Company_Management database established");
		} catch (SQLException e) {
			System.out.println("Connect to Company_Management database failed!");
			throw new SQLException(e.getErrorCode() + ": " + e.getMessage());
		}
		return dbConnection;
	}
}