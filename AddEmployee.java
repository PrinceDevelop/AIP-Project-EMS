package CRUDEmployee;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.EmployeeDAO;
import DAO.Employee;  // Assuming Employee is a DAO model

@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // ✅ Handle GET request (to display the form or show an error if GET is not allowed)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h3 style='color:red;'>Error: Use the form to submit data!</h3>");
    }

    // ✅ Handle POST request to add employee
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String jobTitle = request.getParameter("jobTitle");
        String appliedDate = request.getParameter("appliedDate");

        // Validate input
        if (name == null || email == null || jobTitle == null || appliedDate == null ||
            name.trim().isEmpty() || email.trim().isEmpty() || jobTitle.trim().isEmpty() || appliedDate.trim().isEmpty()) {
            response.getWriter().println("<h3 style='color:red;'>Error: All fields are required!</h3>");
            return;
        }

        // Parse experience and salary safely
        int experience = 0;
        double salary = 0;
        try {
            experience = Integer.parseInt(request.getParameter("experience"));
            salary = Double.parseDouble(request.getParameter("salary"));
        } catch (NumberFormatException e) {
            response.getWriter().println("<h3 style='color:red;'>Error: Invalid number format for Experience or Salary!</h3>");
            return;
        }

        // Create employee object
        Employee emp = new Employee(name, email, jobTitle, experience, salary, appliedDate);

        // Attempt to add employee via DAO
        boolean success = EmployeeDAO.insertEmployee(emp);
        if (success) {
            response.sendRedirect("dashboard.jsp"); // Redirect to dashboard if success
        } else {
            response.getWriter().println("<h3 style='color:red;'>Error: Unable to add employee!</h3>");
        }
    }
}
