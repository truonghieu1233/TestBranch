    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package dao;

    import java.sql.*;

    /**
     *
     * @author asus
     */
    public class DbConnection {

    protected Connection connection;

    /**
     * get an connection
     *
     * @return connection or null
     * @throws ClassNotFoundException
     */
    public Connection getConnection() {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                // ĐÃ THÊM tham số bảo mật ở cuối URL
                String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJ301_FPTUniversity;encrypt=true;trustServerCertificate=true";
                String user = "sa";
                String password = "ahihi1233210";
                connection = DriverManager.getConnection(url, user, password);
                return connection;
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println("Error " + e.getMessage() + " at DBContext");
                return null;
            }
        }

   
}

