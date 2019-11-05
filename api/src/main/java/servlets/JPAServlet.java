package servlets;

import entities.Item;
import entities.Mark;
import entities.ShoppingList;
import entities.User;
import zrna.ItemsBean;
import zrna.MarksBean;
import zrna.ShoppingListsBean;
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

    @Inject
    private ShoppingListsBean shoppingListsBean;

    @Inject
    private MarksBean marksBean;

    @Inject
    private ItemsBean itemsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; chatset=UTF-8");
        PrintWriter printWriter = resp.getWriter();

        printWriter.println("<html><body>");

        List<User> users = usersBean.getAllUsers();
        printWriter.println("<h2>Uporabniki</h2>");
        for (User u: users) {
            printWriter.printf("<ul>%s %s %s %s</ul>", u.getId(), u.getName(), u.getLastName(), u.getUsername());
        }

        List<ShoppingList> shoppingLists = shoppingListsBean.getAllShoppingLists();
        printWriter.println("<h2>Nakupovalni seznami</h2>");
        for (ShoppingList s: shoppingLists) {
            printWriter.printf("<ul>%s %s %s</ul>", s.getId(), s.getName(), s.getDescription());
        }

        List<Mark> marks = marksBean.getAllMarks();
        printWriter.println("<h2>Oznake</h2>");
        for (Mark m: marks) {
            printWriter.printf("<ul>%s %s %s</ul>", m.getId(), m.getAddress(), m.getDescription());
        }

        List<Item> items = itemsBean.getAllItems();
        printWriter.println("<h2>Artikli</h2>");
        for (Item i: items) {
            printWriter.printf("<ul>%s %s %s</ul>", i.getId(), i.getName(), i.getDescription());
        }

        printWriter.println("</body></html>");
        printWriter.close();

    }
}
