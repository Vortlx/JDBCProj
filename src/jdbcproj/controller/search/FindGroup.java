package jdbcproj.controller.search;


import jdbcproj.dao.DAOGroup;
import jdbcproj.dao.daogroup.DAOGroupConnection;
import jdbcproj.data.Group;

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
 * Servlet search groups with specific properties
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class FindGroup extends HttpServlet {

    private static final long serialVersionUID = 7346289375035L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        DAOGroup connToGroup = new DAOGroupConnection();

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
            e.printStackTrace();
        }

        ServletContext servletContext = getServletContext();
        RequestDispatcher disp = servletContext.getRequestDispatcher("/jsp/search/GroupsSearch.jsp");
        disp.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doGet(req, res);
    }
}
