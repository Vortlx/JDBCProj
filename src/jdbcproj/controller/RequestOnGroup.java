package jdbcproj.controller;

import jdbcproj.dao.togroup.DAOGroup;
import jdbcproj.dao.toperson.DAOTeachers;
import jdbcproj.data.group.Group;
import jdbcproj.data.person.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lebedevas on 09.09.16.
 */
public class RequestOnGroup extends HttpServlet {

    private static final long serialVersionUID = 7346289375035L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        DAOGroup connToGroup = new DAOGroup();

        String name = req.getParameter("name");

        List<Group> list = new ArrayList<Group>();
        try{
            if("".equals(name)){
                list = connToGroup.getAll();
            }else if(!"".equals(name)) {
                Group oneGroup = connToGroup.getByName(name);
                list.add(oneGroup);
            }
            req.setAttribute("groups", list);
        }catch(SQLException e){
            list.add(new Group());
        }

        ServletContext servletContext = getServletContext();
        RequestDispatcher disp = servletContext.getRequestDispatcher("jsp/teachers.jsp");
        disp.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doGet(req, res);
    }
}