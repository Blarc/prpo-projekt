package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet")
public class Servlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; chatset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html><body>");

        printWriter.println("<h1>Priporocila</h1>");
        printWriter.println("<p>Dobrodosli na pomozni storitvi.</p>");

        printWriter.println("</body></html>");
        printWriter.close();

    }
}
