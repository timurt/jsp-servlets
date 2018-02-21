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
    <title>Simple crawler</title>
    <link rel="stylesheet" href="css/bootstrap.css">

</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Simple crawler</a>
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

                <form action="crawl" method="post" enctype="multipart/form-data">
                    <button type="submit" class="btn btn-primary">Crawl CNN Sports</button>
                </form>

                <c:if test="${requestScope.news != null}">
                    <hr/>
                    <h2>Results</h2>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Update date</th>
                            <th>Url</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.news}" var="item">
                            <tr>
                                <td><c:out value="${item.title}" /></td>
                                <td><c:out value="${item.author}" /></td>
                                <td><c:out value="${item.updateDate}" /></td>
                                <td><c:out value="${item.url}" /></td>
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
