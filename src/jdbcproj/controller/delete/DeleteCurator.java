package jdbcproj.controller.delete;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.dao.daoteachers.DAOTeachers;
import jdbcproj.dao.daoteachers.DAOTeachersConnection;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Servlet delete curator of specific group
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class DeleteCurator extends HttpServlet {

    private static final long serialVersionUID = 25370000004441L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        DAOTeachers dao = new DAOTeachersConnection();

        try{
            String teacherName = req.getParameter("teacherName");
            String teacherFamilyName = req.getParameter("teacherFamilyName");
            String groupName= req.getParameter("groupName");

            dao.deleteCurator(teacherName, teacherFamilyName, groupName);
        }catch(SQLException e){
            e.printStackTrace();
        }

        RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/search/TeachersSearch.jsp");
        reqDisp.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doGet(req, res);
    }
}
