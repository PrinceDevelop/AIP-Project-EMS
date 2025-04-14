package CRUDEmployee;

import DAO.Employee;
import DAO.EmployeeDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/EditEmployee")
public class EditEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                Employee employee = EmployeeDAO.getEmployeeById(id);

                if (employee != null) {
                    request.setAttribute("employee", employee); // ✅ attribute name matches JSP
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/EditEmployee.jsp"); // ✅ match JSP name
                    dispatcher.forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee ID format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Employee ID is required");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String jobTitle = request.getParameter("jobTitle");
        String experienceParam = request.getParameter("experience");
        String salaryParam = request.getParameter("salary");
        String appliedDate = request.getParameter("appliedDate");

        try {
            if (idParam == null || name == null || email == null || jobTitle == null || 
                experienceParam == null || salaryParam == null || appliedDate == null) {
                throw new IllegalArgumentException("All fields are required");
            }

            int id = Integer.parseInt(idParam);
            int experience = Integer.parseInt(experienceParam);
            double salary = Double.parseDouble(salaryParam);

            if (!email.contains("@")) {
                throw new IllegalArgumentException("Invalid email format");
            }

            if (experience < 0) {
                throw new IllegalArgumentException("Experience cannot be negative");
            }

            if (salary < 0) {
                throw new IllegalArgumentException("Salary must be a positive number");
            }

            Employee employee = new Employee(id, name, email, jobTitle, experience, salary, appliedDate);

            if (EmployeeDAO.updateEmployee(employee)) {
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update employee");
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format for experience or salary");
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
