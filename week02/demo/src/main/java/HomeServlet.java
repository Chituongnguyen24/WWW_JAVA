import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> courses = List.of("Java", "HTML", "CSS", "JSP");
        request.setAttribute("username", "Chí Tường");
        request.setAttribute("courses", courses);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
