package Servelet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Archibald on 12/12/2016.
 */
@WebServlet(name = "Servlet1")
public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session=request.getSession();
      session.setAttribute("aaa","I'm session");
      ServletContext context=request.getServletContext();
      context.setAttribute("aaa","I'm context");
      Cookie cookie=new Cookie("asd","4444");
      response.addCookie(cookie);
      request.getCookies();
    }
}
