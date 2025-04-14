<%-- 
    Document   : AddEmployee
    Created on : 14 Apr 2025, 4:56:06 pm
    Author     : Prince
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Employee</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="bg-gray-100 p-6">
        <div class="max-w-lg mx-auto bg-white p-6 rounded-lg shadow-lg">
            <h1 class="text-2xl font-bold mb-4">Add Employee</h1>

            <!-- ✅ Form ka action sahi kiya (AddEmployee Servlet ko call karega) -->
            <form action="AddEmployee" method="post" class="space-y-4">
                <div>
                    <label class="block font-semibold">Applicant Name</label>
                    <input type="text" name="name" required class="w-full p-2 border rounded-lg">
                </div>
                <div>
                    <label class="block font-semibold">Email</label>
                    <input type="email" name="email" required class="w-full p-2 border rounded-lg">
                </div>
                <div>
                    <label class="block font-semibold">Job Title</label>
                    <input type="text" name="jobTitle" required class="w-full p-2 border rounded-lg">
                </div>
                <div>
                    <label class="block font-semibold">Experience (Years)</label>
                    <input type="number" name="experience" required class="w-full p-2 border rounded-lg">
                </div>
                <div>
                    <label class="block font-semibold">Salary</label>
                    <input type="text" name="salary" required class="w-full p-2 border rounded-lg">
                </div>
                <div>
                    <label class="block font-semibold">Applied Date</label>
                    <input type="date" name="appliedDate" required class="w-full p-2 border rounded-lg">
                </div>

                <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-800">Submit</button>
            </form>
        </div>
    </body>
</html>