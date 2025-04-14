<%-- 
    Document   : Logout
    Created on : 14 Apr 2025, 4:59:53 pm
    Author     : Prince
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session != null) {
        session.invalidate(); // Invalidate session only once
    }
    response.sendRedirect("index.html"); // Redirect to login page
%>
