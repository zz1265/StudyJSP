package crtl;

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Archibald on 12/14/2016.
 */
@WebServlet(name = "ShowByPage")
public class ShowByPage extends HttpServlet {
    UserDao userDao=new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page1=request.getParameter("page");
        int page=Integer.parseInt(page1);
        ArrayList<User> arrayList=null;
        int allpage=0;
        try {
            arrayList=userDao.getUserByPage(page,3);
            allpage=userDao.getAllPage(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession httpSession=request.getSession();
        httpSession.setAttribute("curpage",page);
        httpSession.setAttribute("allpage",allpage);
        httpSession.setAttribute("Users",arrayList);
        request.getRequestDispatcher("page.jsp").forward(request,response);
    }
}
