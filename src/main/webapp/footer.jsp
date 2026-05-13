<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String course = request.getParameter("course");
    if(course == null) course = "PRJ301";
%>

<div style="
    margin-top:30px;
    padding:14px 10px;
    text-align:center;
    color:#666;
    font-size:14px;
    border-top:1px solid #eee;
">
    © 2026 Book Management System <br>
    Course: <b><%= course %></b>
</div>
