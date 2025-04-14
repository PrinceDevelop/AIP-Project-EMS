package CRUDEmployee;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.EmployeeDAO;

@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET request to delete employee
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            String idParam = request.getParameter("id");

            if (idParam == null || idParam.trim().isEmpty()) {
                response.getWriter().println("<h3 style='color:red;'>❌ Error: Missing employee ID!</h3>");
                return;
            }

            int id = Integer.parseInt(idParam);

            // Try to delete employee
            boolean deleted = EmployeeDAO.deleteEmployee(id);

            if (deleted) {
                // Success
                response.sendRedirect("dashboard.jsp");
            } else {
                // Employee with given ID not found or SQL issue
                response.getWriter().println("<h3 style='color:red;'>❌ Error: Unable to delete employee. Please check if the employee ID exists.</h3>");
            }

        } catch (NumberFormatException e) {
            response.getWriter().println("<h3 style='color:red;'>❌ Error: Invalid employee ID format!</h3>");
        } catch (IOException e) {
            // Print full stack trace for debugging
            response.getWriter().println("<h3 style='color:red;'>❌ Unexpected Error: " + e.getMessage() + "</h3>");
            e.printStackTrace(response.getWriter());
        }
    }
}
