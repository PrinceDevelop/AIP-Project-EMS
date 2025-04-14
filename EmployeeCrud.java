package CRUDEmployee;

import DAO.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCrud {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Prince@123";

    // Get database connection
    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }

    // INSERT EMPLOYEE (CREATE)
    public static boolean insertEmployee(Employee emp) {
        String sql = "INSERT INTO employee (name, email, jobtitle, experience, salary, applieddate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getJobTitle());
            ps.setInt(4, emp.getExperience());
            ps.setDouble(5, emp.getSalary());
            ps.setString(6, emp.getAppliedDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Insert Error: " + e.getMessage());
            return false;
        }
    }

    // GET ALL EMPLOYEES (READ)
    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("jobtitle"),
                    rs.getInt("experience"),
                    rs.getDouble("salary"),
                    rs.getString("applieddate")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Fetch All Error: " + e.getMessage());
        }
        return employees;
    }

    // GET EMPLOYEE BY ID
    public static Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("jobtitle"),
                        rs.getInt("experience"),
                        rs.getDouble("salary"),
                        rs.getString("applieddate")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Fetch by ID Error: " + e.getMessage());
        }
        return null;
    }

    // UPDATE EMPLOYEE
    public static boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET name=?, email=?, jobtitle=?, experience=?, salary=?, applieddate=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getJobTitle());
            ps.setInt(4, emp.getExperience());
            ps.setDouble(5, emp.getSalary());
            ps.setString(6, emp.getAppliedDate());
            ps.setInt(7, emp.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update Error: " + e.getMessage());
            return false;
        }
    }

    // DELETE EMPLOYEE
    public static boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Delete Error: " + e.getMessage());
            return false;
        }
    }
}
