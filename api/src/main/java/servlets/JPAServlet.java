package servlets;

import entities.User;
import zrna.UsersBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/html; chatset=UTF-8");
        PrintWriter printWriter = resp.getWriter();

        List<User> users = usersBean.getUsers();


        printWriter.println("<html><body>");
        printWriter.println("<h2>Uporabniki</h2>");
        for (User u: users) {
            printWriter.printf("<ul>%s %s %s %s</ul>", u.getId(), u.getName(), u.getLastName(), u.getUsername());
        }

        printWriter.println("</body></html>");
        printWriter.close();

    }
}
