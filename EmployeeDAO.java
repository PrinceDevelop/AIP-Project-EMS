package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class EmployeeDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Prince@123";
    private static final Logger LOGGER = Logger.getLogger(EmployeeDAO.class.getName());
    // Method to get database connection
    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }
    // Insert employee (CREATE)
    public static boolean insertEmployee(Employee emp) {
        String sql = "INSERT INTO employees (name, email, jobtitle, experience, salary, applieddate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getJobTitle());
            ps.setInt(4, emp.getExperience());
            ps.setDouble(5, emp.getSalary());
            ps.setString(6, emp.getAppliedDate());
            // Execute the query and return whether the row was inserted
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting employee", e);
            return false;
        }
    }
    // Get all employees (READ)
    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
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
            LOGGER.log(Level.SEVERE, "Error fetching employees", e);
        }
        return employees;
    }

    // Get employee by ID (READ)
    public static Employee getEmployeeById(int id) {
        Employee emp = null;
        String query = "SELECT * FROM employees WHERE id = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("jobtitle"),
                        rs.getInt("experience"),
                        rs.getDouble("salary"),
                        rs.getString("applieddate")
                );
            }
        } catch (SQLException e) {
        }
        return emp;
    }

    // Update employee (UPDATE)
    public static boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET name=?, email=?, jobtitle=?, experience=?, salary=?, applieddate=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getJobTitle());
            ps.setInt(4, emp.getExperience());
            ps.setDouble(5, emp.getSalary());
            ps.setString(6, emp.getAppliedDate());
            ps.setInt(7, emp.getId());

            // Execute the update and return whether the update was successful
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating employee with ID: " + emp.getId(), e);
            return false;
        }
    }
    // Delete employee (DELETE)
    public static boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println("🗑️ Deleting employee with ID: " + id + " | Rows affected: " + rows);
            return rows > 0;
        } catch (SQLException e) { // Log detailed SQL error
            // Log detailed SQL error
            return false;
        }
    }
}
