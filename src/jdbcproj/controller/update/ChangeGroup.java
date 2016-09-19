package jdbcproj.controller.update;


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
 * Servlet change name of the student group in database
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class ChangeGroup extends HttpServlet{
   
	private static final long serialVersionUID = 2620571141L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
    	DAOStudents dao = new DAOStudentsConnection();
    	
    	try{
    		String studentName = req.getParameter("studentName");
    		String studentFamilyName = req.getParameter("studentFamilyName");
    		String newGroupName = req.getParameter("newGroupName");
    		
    		dao.updateGroup(studentName, studentFamilyName, newGroupName);
    	}catch(SQLException e){
    		e.printStackTrace();
            String message = "Can't do this operation.";
            req.setAttribute("message", message);

            RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/update/ChangeGroup.jsp");
            reqDisp.forward(req, res);
    	}
    	
    	RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/search/StudentsSearch.jsp");
        reqDisp.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doGet(req, res);
    }
}
