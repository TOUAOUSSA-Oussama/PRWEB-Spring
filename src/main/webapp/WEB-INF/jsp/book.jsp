<%-- 
    Document   : book
    Created on : 25 févr. 2022, 17:57:35
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
        <link rel="stylesheet" href="css/style.css">
        <title>Update Book</title>
    </head>
    <body>
        <div class="list">
            <h1>Update book :</h1>
            <form method="POST" action="saveBook.do">
                <table>
                    <tr>
                        <th>Book #</th>
                        <td>
                            <c:choose>
                                <c:when test="${empty book.bookId}">NEW<input type="hidden" name="id" value="-1" /></c:when>
                                <c:otherwise>${book.bookId}<input type="hidden" name="id" value="${book.bookId}"/></c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th>Book Title</th>
                        <td><input type="text" name="BookTitle" value="${book.bookTitle}"/></td>
                    </tr>
                    <tr>
                        <th>Book Authors</th>
                        <td><input type="text" name="BookAuthors" value="${book.bookAuthors}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button class="saveBtn">Save</button>
                        </td>
                    </tr>
                </table>
            </form>

        </div>
    </body>
</html>
