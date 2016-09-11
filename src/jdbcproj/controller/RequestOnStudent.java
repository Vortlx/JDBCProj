package jdbcproj.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.dao.toperson.DAOStudents;
import jdbcproj.data.person.Student;

public class RequestOnStudent extends HttpServlet{

	private static final long serialVersionUID = 2387567823658L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		DAOStudents connToStudent = new DAOStudents();
		
		String name = req.getParameter("name");
		String familyName = req.getParameter("familyName");
		
		List<Student> list = new ArrayList<Student>();
		try{
			if("".equals(name) && "".equals(familyName)){
				list = connToStudent.getAll();
			}else if(!"".equals(name) && "".equals(familyName)){
				list = connToStudent.getByName(name);
			} else if("".equals(name) && !"".equals(familyName)){
				list = connToStudent.getByFamilyName(familyName);
			}else{
				list = connToStudent.getStudent(name, familyName);
			}
		
			req.setAttribute("students", list);
		}catch(SQLException e){
			e.printStackTrace();
		}

		ServletContext servletContext = getServletContext();
		RequestDispatcher disp = servletContext.getRequestDispatcher("/jsp/StudentsList.jsp");
		disp.forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}
}