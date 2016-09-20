package jdbcproj.controller.delete;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.dao.DAOGroup;
import jdbcproj.dao.daogroup.DAOGroupConnection;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

/**
 * Servlet delete group with specific name
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class DeleteGroup extends HttpServlet{

	private static final long serialVersionUID = 25376352337241L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		DAOGroup dao = new DAOGroupConnection();
		
		String name = req.getParameter("name");
		String mes = "";
		try{
			dao.delete(name);
			mes = "Operation was success";
		}catch(SQLException e){
			mes = "Can't do this operation.";
			e.printStackTrace();
		}

		req.setAttribute("message", mes);
		
		RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/delete/DeleteGroup.jsp");
		reqDisp.forward(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}
}