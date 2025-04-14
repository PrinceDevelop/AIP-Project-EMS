<%-- 
    Document   : editEmployee
    Created on : 14 Apr 2025, 6:25:31 pm
    Author     : Prince
--%>


<%@ page import="DAO.Employee" %>
<%@ page import="DAO.EmployeeDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Employee</title>
</head>
<body>
    <h2>Edit Employee</h2>

    <%
        Employee employee = (Employee) request.getAttribute("employee"); // ✅ fixed: use "employee" instead of "employees"
        if (employee == null) {
            out.println("<p>Error: Employee not found.</p>");
            return;
        }
    %>

    <form action="EditEmployee" method="post">
        <input type="hidden" name="id" value="<%= employee.getId() %>" />

        <label for="name">Name:</label>
        <input type="text" name="name" value="<%= employee.getName() %>" required /><br/><br/>

        <label for="email">Email:</label>
        <input type="email" name="email" value="<%= employee.getEmail() %>" required /><br/><br/>

        <label for="jobTitle">Job Title:</label>
        <input type="text" name="jobTitle" value="<%= employee.getJobTitle() %>" required /><br/><br/>

        <label for="experience">Experience:</label>
        <input type="number" name="experience" value="<%= employee.getExperience() %>" required /><br/><br/>

        <label for="salary">Salary:</label>
        <input type="number" step="0.01" name="salary" value="<%= employee.getSalary() %>" required /><br/><br/>

        <label for="appliedDate">Applied Date:</label>
        <input type="text" name="appliedDate" value="<%= employee.getAppliedDate() %>" required /><br/><br/>

        <input type="submit" value="Update Employee" />
    </form>
</body>
</html>

