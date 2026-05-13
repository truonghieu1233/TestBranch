package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book;

public class BookDAO extends DbConnection {

    public List<Book> getAll() {
        List<Book> list = new ArrayList<>();
        String sql = """
                SELECT b.id, b.title, b.author, b.price, b.category_id,
                       c.name AS category_name
                FROM books b
                JOIN categories c ON b.category_id = c.id
                ORDER BY b.id DESC
                """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setPrice(rs.getFloat("price"));
                b.setCategoryId(rs.getInt("category_id"));
                b.setCategoryName(rs.getString("category_name"));
                list.add(b);
            }

        } catch (SQLException e) {
            System.err.println("BookDAO.getAll error: " + e.getMessage());
        }

        return list;
    }

    public Book getById(int id) {
        String sql = """
                SELECT b.id, b.title, b.author, b.price, b.category_id,
                       c.name AS category_name
                FROM books b
                JOIN categories c ON b.category_id = c.id
                WHERE b.id = ?
                """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book b = new Book();
                    b.setId(rs.getInt("id"));
                    b.setTitle(rs.getString("title"));
                    b.setAuthor(rs.getString("author"));
                    b.setPrice(rs.getFloat("price"));
                    b.setCategoryId(rs.getInt("category_id"));
                    b.setCategoryName(rs.getString("category_name"));
                    return b;
                }
            }

        } catch (SQLException e) {
            System.err.println("BookDAO.getById error: " + e.getMessage());
        }
        return null;
    }

    public boolean insert(Book b) {
        String sql = "INSERT INTO books(title, author, price, category_id) VALUES(?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setFloat(3, b.getPrice());
            ps.setInt(4, b.getCategoryId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("BookDAO.insert error: " + e.getMessage());
        }
        return false;
    }

    public boolean update(Book b) {
        String sql = "UPDATE books SET title = ?, author = ?, price = ?, category_id = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setFloat(3, b.getPrice());
            ps.setInt(4, b.getCategoryId());
            ps.setInt(5, b.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("BookDAO.update error: " + e.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("BookDAO.delete error: " + e.getMessage());
        }
        return false;
    }

public List<Book> search(String keyword) {
    List<Book> list = new ArrayList<>();
    String sql = """
        SELECT b.id, b.title, b.author, b.price,
               b.category_id, c.name AS category_name
        FROM books b
        JOIN categories c ON b.category_id = c.id
        WHERE b.title LIKE ? OR b.author LIKE ?
    """;

    try (Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        String key = "%" + (keyword == null ? "" : keyword) + "%";
        ps.setString(1, key);
        ps.setString(2, key);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Book b = new Book();
            b.setId(rs.getInt("id"));
            b.setTitle(rs.getString("title"));
            b.setAuthor(rs.getString("author"));
            b.setPrice(rs.getFloat("price"));
            b.setCategoryId(rs.getInt("category_id"));
            b.setCategoryName(rs.getString("category_name"));
            list.add(b);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
    
}
