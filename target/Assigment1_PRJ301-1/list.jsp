<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Management</title>
    <style>
        body{
            font-family: Arial, sans-serif;
            background:#f2f2f2;
            margin:0;
            padding:30px 12px;
        }
        .container{
            max-width:1200px;
            margin:0 auto;
            display:grid;
            grid-template-columns: 2.2fr 1fr;
            gap:20px;
        }
        .card{
            background:#fff;
            border-radius:12px;
            box-shadow:0 10px 25px rgba(0,0,0,0.08);
            overflow:hidden;
        }
        .card-header{
            padding:16px 20px;
            border-bottom:1px solid #eee;
            font-size:20px;
            font-weight:700;
            display:flex;
            justify-content:space-between;
            align-items:center;
        }
        .btn{
            padding:8px 12px;
            background:#2196F3;
            color:#fff;
            text-decoration:none;
            border-radius:8px;
            font-weight:600;
            font-size:14px;
            border:0;
            cursor:pointer;
        }
        .btn:hover{ background:#1976D2; }

        table{
            width:100%;
            border-collapse:collapse;
        }
        th, td{
            padding:12px 14px;
            border-bottom:1px solid #eee;
            text-align:left;
        }
        th{
            background:#fafafa;
            font-size:13px;
            text-transform:uppercase;
            color:#444;
        }
        tr:hover td{ background:#fcfcfc; }

        .col-id{ width:70px; }
        .col-price{ width:130px; }
        .col-cat{ width:180px; }
        .col-actions{ width:170px; }

        .actions{
            display:flex;
            gap:8px;
        }
        .a-btn{
            padding:7px 10px;
            border-radius:8px;
            text-decoration:none;
            font-weight:700;
            font-size:13px;
            display:inline-block;
            border:0;
            cursor:pointer;
        }
        .a-edit{
            background:#E3F2FD;
            color:#1565C0;
        }
        .a-del{
            background:#FFEBEE;
            color:#C62828;
        }

        .form-body{
            padding:18px 20px 22px;
        }
        label{
            display:block;
            margin-top:14px;
            font-size:13px;
            font-weight:700;
        }
        input, select{
            width:100%;
            margin-top:6px;
            padding:10px 12px;
            border-radius:8px;
            border:1px solid #ccc;
            box-sizing:border-box;
            background:#fff;
        }
        input:focus, select:focus{
            outline:none;
            border-color:#2196F3;
            box-shadow:0 0 0 3px rgba(33,150,243,.15);
        }
        input[readonly]{
            background:#f5f5f5;
            color:#555;
            border-color:#ddd;
            cursor:not-allowed;
        }
        .row{
            display:flex;
            gap:12px;
        }
        .row > div{ flex:1; }

        .form-actions{
            display:flex;
            gap:10px;
            margin-top:18px;
        }
        .btn-save{
            flex:1;
            background:#4CAF50;
            color:#fff;
            border:none;
            padding:10px;
            border-radius:8px;
            font-weight:800;
            cursor:pointer;
        }
        .btn-save:hover{ background:#43A047; }
        .btn-cancel{
            flex:1;
            background:#e5e7eb;
            color:#333;
            text-decoration:none;
            display:flex;
            align-items:center;
            justify-content:center;
            border-radius:8px;
            font-weight:700;
        }
        .error{
            margin-top:12px;
            color:red;
            text-align:center;
            min-height:18px;
        }

        .badge{
            font-size:12px;
            font-weight:700;
            padding:6px 10px;
            border-radius:999px;
            background:#f1f5f9;
            color:#334155;
        }

        @media (max-width: 900px){
            .container{ grid-template-columns: 1fr; }
        }
    </style>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <!-- ================== FORM 1: BOOK LIST ================== -->
    <form class="card" action="book" method="GET">
       <div class="card-header">
    <span>Book List</span>

    <div style="display:flex; gap:10px; align-items:center;">
        <!-- SEARCH -->
        <input type="text"
               name="keyword"
               value="${param.keyword}"
               placeholder="Search title / author..."
               style="
                   padding:8px 12px;
                   border-radius:8px;
                   border:1px solid #ccc;
                   width:200px;
               ">

<input type="hidden" name="action" value="search">
<button class="btn" type="submit">Search</button>
<a class="btn" href="book" style="background:#9ca3af;">Refresh</a>

        <span class="badge">
            Total: <c:out value="${empty books ? 0 : books.size()}"/>
        </span>
    </div>
</div>

        <table>
            <thead>
            <tr>
                <th class="col-id">ID</th>
                <th>Title</th>
                <th>Author</th>
                <th class="col-price">Price</th>
                <th class="col-cat">Category</th>
                <th class="col-actions">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${empty books}">
                    <tr>
                        <td colspan="6" style="text-align:center; padding:18px; color:#666;">
                            No books found.
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${books}" var="b">
                        <tr>
                            <td data-name="id">${b.id}</td>

                            <td data-name="title">${b.title}</td>
                            <td data-name="author">${b.author}</td>
                            <td data-name="price">${b.price}</td>

                            <!-- hiển thị category name -->
                            <td data-name="categoryName">${b.categoryName}</td>

                            <!-- giữ categoryId ẩn cho JS lấy -->
                            <td data-name="categoryId" style="display:none;">${b.categoryId}</td>

                            <td>
                                <div class="actions">
                                    <button type="button" class="a-btn a-edit" onclick="editBook(this)">Edit</button>
                                    <button type="button" class="a-btn a-del" onclick="deleteBook(this)">Delete</button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </form>

    <!-- ================== FORM 2: BOOK FORM (ADD/UPDATE/DELETE) ================== -->
    <form id="bookForm" class="card" action="book" method="post">
        <input type="hidden" name="action" value="add">
        <div class="card-header">
            <span>Book Form</span>
            <span class="badge" id="modeBadge">ADD</span>
        </div>

        <div class="form-body">
            <label>ID</label>
            <input type="text" name="id" value="" readonly>

            <label>Title</label>
            <input type="text" name="title" value="" required>

            <label>Author</label>
            <input type="text" name="author" value="" required>

            <div class="row">
                <div>
                    <label>Price</label>
                    <input type="number" name="price" step="0.01" min="0" value="" required>
                </div>
                <div>
                    <label>Category</label>
                    <select name="categoryId" required>
                        <c:forEach items="${categories}" var="c">
                            <option value="${c.id}">${c.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="error">
                <c:out value="${error}"/>
            </div>

            <div class="form-actions">
                <button class="btn-save" type="submit">Save</button>
                <a class="btn-cancel" href="#" onclick="resetBookForm(); return false;">Cancel</a>
            </div>
        </div>
    </form>
</div>

<script>
    function editBook(btn){
        const tr = btn.closest("tr");
        const form = document.getElementById("bookForm");

        form.querySelector('input[name="id"]').value =
            tr.querySelector('td[data-name="id"]').textContent.trim();

        form.querySelector('input[name="title"]').value =
            tr.querySelector('td[data-name="title"]').textContent.trim();

        form.querySelector('input[name="author"]').value =
            tr.querySelector('td[data-name="author"]').textContent.trim();

        form.querySelector('input[name="price"]').value =
            tr.querySelector('td[data-name="price"]').textContent.trim();

        const categoryId = tr.querySelector('td[data-name="categoryId"]').textContent.trim();
        form.querySelector('select[name="categoryId"]').value = categoryId;

        form.querySelector('input[name="action"]').value = "update";
        document.getElementById("modeBadge").textContent = "UPDATE";
    }

    function deleteBook(btn){
        if(!confirm("Delete this book?")) return;

        const tr = btn.closest("tr");
        const id = tr.querySelector('td[data-name="id"]').textContent.trim();

        const form = document.getElementById("bookForm");
        form.querySelector('input[name="id"]').value = id;
        form.querySelector('input[name="action"]').value = "delete";

        form.submit();
    }

    function resetBookForm(){
        const form = document.getElementById("bookForm");
        form.reset();

        // ID readonly nên reset có thể không clear trong vài browser -> set tay
        form.querySelector('input[name="id"]').value = "";

        form.querySelector('input[name="action"]').value = "add";
        document.getElementById("modeBadge").textContent = "ADD";
    }
</script>

</body>
<jsp:include page="footer.jsp">
    <jsp:param name="course" value="PRJ301"/>
</jsp:include>


</html>
