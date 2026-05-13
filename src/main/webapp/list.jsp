<%-- 
    Document   : list
    Created on : Jan 8, 2026, 1:37:32 PM
    Author     : asus
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student</title>
    <style>
        .box {
            width: 600px;
            border: 1px solid black;
            padding: 20px;
            margin-bottom: 30px;
        }
        table {
            width: 100%;
        }
        td, th {
            padding: 5px;
        }
        th {
            text-align: left;
        }
    </style>
</head>
<body>

<!-- ===== FORM NHẬP THÔNG TIN ===== -->
<div class="box">
    <h3>Information of Student</h3>

    <form method="post" action="student">
        <table>
            <tr>
                <td>ID</td>
                <td><input type="text" name="id"></td>
            </tr>

            <tr>
                <td>Name</td>
                <td><input type="text" name="name"></td>
            </tr>

            <tr>
                <td>Age</td>
                <td><input type="text" name="age"></td>
            </tr>
            <tr>
                <td>Gender</td>
                <td>
                   Male <input type="radio" name="gender" value="Male" />
                   Female <input type="radio" name="gender" value="Female" />
                </td>
            </tr>
            <tr>
                <td>Hobbies</td>
                <td>
                    Football <input type="checkbox" name="hobbies" value="Football">
                    Cooking <input type="checkbox" name="hobbies" value="Cooking">
                    Programming <input type="checkbox" name="hobbies" value="Programming">
                </td>
            </tr>

            <tr>
                <td></td>
                <td><input type="submit" name="submit" value="Submit"></td>
            </tr>
        </table>
    </form>
</div>

<!-- ===== FORM HIỂN THỊ DANH SÁCH ===== -->

<div class="box">
    <h3 style="text-align:center">List of Student</h3>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>GENDER</th>
            <th>AGE</th>
            <th>HOBBIES</th>
        </tr>

        <c:forEach items="${list}" var = "kkk">
            <tr>
                <td>${kkk.id}</td>
                <td>${kkk.name}</td>
                <td>${kkk.age}</td>
                <td>${kkk.gender}</td>
                <td>Playing game</td>
            </tr>
           
</c:forEach>
    </table>
</div>

</body>
</html>
