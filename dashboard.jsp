<%-- 
    Document   : dashboard
    Created on : 14 Apr 2025, 4:58:08 pm
    Author     : Prince
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    String userName = (sessionObj.getAttribute("user") != null) ? (String) sessionObj.getAttribute("user") : "User";
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Applicant Details</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
</head>
<body class="bg-gray-100 p-6">
    <div class="max-w-7xl mx-auto bg-white p-6 rounded-lg shadow-lg">
        <div class="flex justify-between items-center mb-4">
            <h1 class="text-2xl font-bold">Employee Details</h1>
            <div class="flex space-x-2">
                <button class="bg-gray-200 text-gray-700 px-4 py-2 rounded-lg flex items-center">
                    <i class="fas fa-file-export mr-2"></i> Export
                </button>
                <a href="AddEmployee.jsp">
                    <button class="bg-purple-600 text-white px-4 py-2 rounded-lg flex items-center">
                        <i class="fas fa-plus mr-2"></i> Add Employee
                    </button>
                </a>
            </div>
        </div>

        <table class="min-w-full bg-white border border-gray-300 shadow-md rounded-lg">
            <thead>
                <tr class="bg-gray-200">
                    <th class="py-2 px-4 border-b">ID</th>
                    <th class="py-2 px-4 border-b">Applicant Name</th>
                    <th class="py-2 px-4 border-b">Job Title</th>
                    <th class="py-2 px-4 border-b">Experience</th>
                    <th class="py-2 px-4 border-b">Salary</th>
                    <th class="py-2 px-4 border-b">Applied Date</th>
                    <th class="py-2 px-4 border-b">Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver"); 
                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db", "root", "Prince@123");
                        String query = "SELECT * FROM employees";
                        stmt = conn.prepareStatement(query);
                        rs = stmt.executeQuery();
                        while (rs.next()) {
                %>
                <tr class="text-center">
                    <td class="py-2 px-4 border-b"><%= rs.getInt("id")%></td>
                    <td class="py-2 px-4 border-b"><%= rs.getString("name")%></td>
                    <td class="py-2 px-4 border-b"><%= rs.getString("jobtitle")%></td>
                    <td class="py-2 px-4 border-b"><%= rs.getString("experience")%></td>
                    <td class="py-2 px-4 border-b"><%= rs.getString("salary")%></td>
                    <td class="py-2 px-4 border-b"><%= (rs.getDate("applieddate") != null) ? rs.getDate("applieddate") : "N/A" %></td>
                    <td class="py-2 px-4 border-b">
                        <div class="flex justify-center space-x-4">
<!--                            <a href="EditEmployee?id=<%= rs.getInt("id") %>" class="text-blue-500 hover:text-blue-700">
                                <i class="fas fa-edit"></i>
                            </a>-->
                             <a href="EditEmployee.jsp?id=${employees.id}" class="text-blue-500 hover:text-blue-700">
                                <i class="fas fa-edit"></i>
                            </a>
                            <a href="DeleteEmployee?id=<%= rs.getInt("id")%>" class="text-red-500 hover:text-red-700" 
                               onclick="return confirm('Are you sure you want to delete this applicant?');">
                                <i class="fas fa-trash-alt"></i>
                            </a>
                        </div>
                    </td>
                </tr>
                <%
                        }
                    } catch (Exception e) {
                        out.println("<p class='text-red-500 font-bold'>Database error: " + e.getMessage() + "</p>");
                    } finally {
                        try {
                            if (rs != null) rs.close();
                            if (stmt != null) stmt.close();
                            if (conn != null) conn.close();
                        } catch (SQLException e) {
                            out.println("<p class='text-red-500'>Error closing resources: " + e.getMessage() + "</p>");
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>