<%-- 
    Document   : books
    Created on : 25 févr. 2022, 17:57:28
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Books page</title>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/myScript.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
    <div class="list">
        <table class="noborder">
            <tr>
                <td class="noborder"><h1>List of books :</h1></td>
                <td class="noborder" style="text-align:right">
                    <form action="switchToUsers.do" method="POST"><button>Switch users</button></form>
                </td>
            </tr>
        </table>
        <table>
            <tr>
                <th>Book #</th>
                <th>Title</th>
                <th>Authors</th>
                <th></th>
            </tr>
            <c:forEach var="anBook" items="${booksList}">
                <tr>
                    <td>${anBook.bookId}</td>
                    <td>${anBook.bookTitle}</td>
                    <td>${anBook.bookAuthors}</td>
                    <td>
                    <form class="iconForm" method="POST" action="#">
                        <input type="hidden" name="id" value="${anBook.bookId}" />
                        <button name="edit" formaction="editBook.do">
                            <img src="img/edit.png" alt="edit" class="icon" />
                        </button>
                        <button  name="delete" formaction="deleteBook.do">
                            <img src="img/delete.png" alt="edit" class="icon" />
                        </button>
                    </form>
                </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="3"></td>
                <td>
                    <form class="iconForm" method="POST" action="createBook.do">
                        <button>
                            <img src="img/plus.png" alt="edit" class="icon" />
                        </button>
                    </form>
                </td>
            </tr>
        </table>

    </div>
</body>

</html>
