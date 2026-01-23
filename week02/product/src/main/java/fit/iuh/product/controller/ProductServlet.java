package fit.iuh.product.controller;

import fit.iuh.product.dao.ProductDAO;
import fit.iuh.product.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
@WebServlet({
        "/list",
        "/new",
        "/insert",
        "/edit",
        "/update",
        "/delete"
})
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getServletPath();

        switch (action) {
            case "/new":
                showForm(req, resp);
                break;
            case "/edit":
                showEditForm(req, resp);
                break;
            case "/delete":
                deleteProduct(req, resp);
                break;
            default:
                listProduct(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getServletPath();

        switch (action) {
            case "/insert":
                insertProduct(req, resp);
                break;
            case "/update":
                updateProduct(req, resp);
                break;
        }
    }

    // LIST
    private void listProduct(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Product> list = productDAO.selectAllProducts();
        req.setAttribute("listProduct", list);
        req.getRequestDispatcher("product-list.jsp").forward(req, resp);
    }

    // FORM
    private void showForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("product-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productDAO.selectProduct(id);
        req.setAttribute("product", product);
        req.getRequestDispatcher("product-form.jsp").forward(req, resp);
    }

    // INSERT
    private void insertProduct(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        Part filePart = req.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        String uploadPath = getServletContext().getRealPath("/uploads");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        if (!fileName.isEmpty()) {
            filePart.write(uploadPath + File.separator + fileName);
        }

        productDAO.insertProduct(new Product(name, price, fileName));
        resp.sendRedirect("list");
    }

    // UPDATE
    private void updateProduct(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        Product old = productDAO.selectProduct(id);

        Part filePart = req.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        String imageName = fileName.isEmpty() ? old.getImage() : fileName;

        if (!fileName.isEmpty()) {
            String uploadPath = getServletContext().getRealPath("/uploads");
            filePart.write(uploadPath + File.separator + fileName);
        }

        productDAO.updateProduct(new Product(id, name, price, imageName));
        resp.sendRedirect("list");
    }

    // DELETE
    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        productDAO.deleteProduct(id);
        resp.sendRedirect("list");
    }
}
