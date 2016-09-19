package jdbcproj.controller.add;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.dao.daostudents.DAOStudents;
import jdbcproj.dao.daostudents.DAOStudentsConnection;

/**
 * Servlet add student with specific name and family name into database
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class AddStudent extends HttpServlet{

	private static final long serialVersionUID = 25323623311141L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		DAOStudents daoStudent = new DAOStudentsConnection();
		
		String name = req.getParameter("name");
		String familyName = req.getParameter("familyName");
		String group = req.getParameter("group");
		
		String message = "";
		
		try{
			daoStudent.add(name, familyName, group);
			message = "Operation was success";
		}catch(SQLException e){
			message = "Can't do this operation.";
			e.printStackTrace();
		}
	
		req.setAttribute("message", message);
		
		RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/add/AddStudent.jsp");
		reqDisp.forward(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}
}
