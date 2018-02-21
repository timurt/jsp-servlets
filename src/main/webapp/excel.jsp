<%--
  Created by IntelliJ IDEA.
  User: timurtibeyev
  Date: 21/1/18
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Excel parser</title>
    <link rel="stylesheet" href="css/bootstrap.css">

</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Excel parser</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>
    <main role="main">
        <div class="album py-5">
            <div class="container">
                <c:if test="${requestScope.error != null}">
                    <div class="alert alert-danger" role="alert">
                        <c:out value="${requestScope.error}" />
                    </div>
                </c:if>

                <form action="excel" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="word">Word</label>
                        <input type="text" class="form-control" id="word" name="word">
                    </div>
                    <div class="form-group">
                        <label for="file">File</label>
                        <input type="file" class="form-control" id="file" name="excelFile"
                               placeholder="Select excel file">
                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>

                <c:if test="${requestScope.occurrences != null}">
                    <hr/>
                    <h2>Occurrences</h2>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Cell value</th>
                            <th>Sheet name</th>
                            <th>Row number</th>
                            <th>Column index</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.occurrences}" var="item">
                            <tr>
                                <td><c:out value="${item.value}" /></td>
                                <td><c:out value="${item.sheet}" /></td>
                                <td><c:out value="${item.row}" /></td>
                                <td><c:out value="${item.column}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </main>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>
