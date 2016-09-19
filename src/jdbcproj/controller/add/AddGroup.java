package jdbcproj.controller.add;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.dao.daogroup.DAOGroup;
import jdbcproj.dao.daogroup.DAOGroupConnection;

/**
 * Servlet add group with specific name into database
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class AddGroup extends HttpServlet{

	private static final long serialVersionUID = 253233311141L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		DAOGroup daoStudent = new DAOGroupConnection();
		
		String name = req.getParameter("name");
		
		String message = "";
		
		try{
			daoStudent.add(name);
			message = "Operation was success";
		}catch(SQLException e){
			message = "Can't do this operation.";
			e.printStackTrace();			
		}
	
		req.setAttribute("message", message);
		
		RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/add/AddGroup.jsp");
		reqDisp.forward(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}
}
