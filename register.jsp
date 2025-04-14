<%-- 
    Document   : register
    Created on : 14 Apr 2025, 5:00:30 pm
    Author     : Prince
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign Up</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    </head>
    <body class="bg-gray-100 flex items-center justify-center min-h-screen">
        <div class="bg-white rounded-lg shadow-lg p-8 max-w-4xl w-full flex flex-col md:flex-row">
            <div class="md:w-1/2">
                <h2 class="text-4xl font-bold mb-8">Sign Up</h2>

                <% if (request.getAttribute("errorMessage") != null) {%>
                <p class="text-red-500"><%= request.getAttribute("errorMessage")%></p>
                <% } %>

                <% if (request.getAttribute("successMessage") != null) {%>
                <p class="text-green-500"><%= request.getAttribute("successMessage")%></p>
                <% }%>

                <form action="register" method="POST">
                    <div class="mb-4 flex items-center border-b border-gray-300 py-2">
                        <i class="fas fa-user text-gray-400 mr-3"></i>
                        <input name="name" required class="appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none" placeholder="Your Name" type="text"/>
                    </div>

                    <div class="mb-4 flex items-center border-b border-gray-300 py-2">
                        <i class="fas fa-envelope text-gray-400 mr-3"></i>
                        <input name="email" required class="appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none" placeholder="Your Email" type="email"/>
                    </div>

                    <div class="mb-4 flex items-center border-b border-gray-300 py-2">
                        <i class="fas fa-lock text-gray-400 mr-3"></i>
                        <input name="password" required class="appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none" placeholder="Password" type="password"/>
                    </div>

                    <div class="mb-4 flex items-center border-b border-gray-300 py-2">
                        <i class="fas fa-lock text-gray-400 mr-3"></i>
                        <input name="confirmPassword" required class="appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none" placeholder="Repeat your password" type="password"/>
                    </div>

                    <div class="mb-4 flex items-center">
                        <input required class="mr-2" type="checkbox"/>
                        <label class="text-gray-600">
                            I agree to all statements in
                            <a class="text-blue-500" href="#">Terms of service</a>
                        </label>
                    </div>

                    <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600">
                        Register
                    </button>
                </form>

                <p class="mt-4 text-center">
                    <a class="text-gray-600" href="index.jsp">I am already a member</a>
                </p>
            </div>

            <div class="md:w-1/2 flex items-center justify-center">
                <img alt="Illustration of a desk with a laptop, plant, and chair" class="w-full max-w-sm" height="300" src="https://storage.googleapis.com/a1aa/image/fI9SGBF2y8E6hdg2KNQKjh06VRIgSLhPeAqomyJU8RU.jpg" width="300"/>
            </div>
        </div>
    </body>
</html>
