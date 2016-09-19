package jdbcproj.controller.add;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.dao.DAOTeachers;
import jdbcproj.dao.DAOTeachersWithConnection;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by lebedevas on 15.09.16.
 */
public class AddCurator extends HttpServlet {
    private static final long serialVersionUID = 25777523771141L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        DAOTeachers daoTeachers = new DAOTeachersWithConnection();

        try{
            String teacherName = req.getParameter("teacherName");
            String teacherFamilyName = req.getParameter("teacherFamilyName");
            String groupName = req.getParameter("groupName");

            daoTeachers.addGroup(teacherName, teacherFamilyName, groupName);

        }catch(SQLException e){
        	e.printStackTrace();
            String message = "Can't do this operation.";
            req.setAttribute("message", message);

            RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/add/AddCurator.jsp");
            reqDisp.forward(req, res);
        }

        RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/search/TeachersSearch.jsp");
        reqDisp.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doGet(req, res);
    }
}
