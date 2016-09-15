package jdbcproj.controller.add;

import jdbcproj.dao.togroup.DAOGroup;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by lebedevas on 15.09.16.
 */
public class AddCurator extends HttpServlet {
    private static final long serialVersionUID = 25777523771141L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try{
            if(false)
                throw (new SQLException());

        }catch(SQLException e){

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
