/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dao.BookDAO;
import dao.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.Category;

/**
 *
 * @author asus
 */
public class BookManagementController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookManagementController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookManagementController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
     
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookDAO bdao = new BookDAO();
        CategoryDAO cdao = new CategoryDAO();

        List<Book> books = bdao.getAll();
        List<Category> categories = cdao.getAll();

        // dùng request.setAttribute như vibe doGet của bạn


        // nếu có action=edit -> đổ dữ liệu lên form
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Book b = bdao.getById(id);
            if (b != null) {
//                request.setAttribute("editId", b.getId());
//                request.setAttribute("editTitle", b.getTitle());
//                request.setAttribute("editAuthor", b.getAuthor());
//                request.setAttribute("editPrice", b.getPrice());
//                request.setAttribute("editCategoryId", b.getCategoryId());
            }
        }
        if ("search".equals(action)) {
        String keyword = request.getParameter("keyword");
        books = bdao.search(keyword);
        } else {
            books = bdao.getAll();
    }
        request.setAttribute("books", books);
        request.setAttribute("categories", categories);

        // forward đúng file jsp bạn đang dùng
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        List<Book> list = new ArrayList<>();
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");

        switch (action) {
            case "add":
                list = insert(request, response);
                break;
            case "update":
                list = update(request, response);
                break;
            case "delete":
                list = delete(request, response);
                break;
            default:
                // nếu action lạ thì load lại list
                BookDAO dao = new BookDAO();
                list = dao.getAll();
                break;
        }

        // vibe y hệt bạn: set session rồi redirect
//        request.getSession().setAttribute("books", list);
        response.sendRedirect("book");
    }

    private List<Book> insert(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        BookDAO dao = new BookDAO();
        Book b = new Book();
        b.setTitle(title);
        b.setAuthor(author);
        b.setPrice(price);
        b.setCategoryId(categoryId);

        dao.insert(b);
        return dao.getAll();
    }

    private List<Book> update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        BookDAO dao = new BookDAO();
        Book b = new Book();
        b.setId(id);
        b.setTitle(title);
        b.setAuthor(author);
        b.setPrice(price);
        b.setCategoryId(categoryId);

        dao.update(b);
        return dao.getAll();
    }

    private List<Book> delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        BookDAO dao = new BookDAO();
        dao.delete(id);

        return dao.getAll();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
