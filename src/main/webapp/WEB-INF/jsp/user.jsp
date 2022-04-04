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
        <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/returnBorrow.js"></script>
        <title>Update user</title>
    </head>
    <body>
        <div class="list">
            <h1>Update user :</h1>
            <form method="POST" action="saveUser.do">
                <table>
                    <tr>
                        <th>User #</th>
                        <td>
                            <c:choose>
                                <c:when test="${empty user.personId}">NEW<input type="hidden" name="id" value="-1" /></c:when>
                                <c:otherwise>${user.personId}<input type="hidden" name="id" value="${user.personId}"/></c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th>FirstName</th>
                        <td><input type="text" name="FirstName" value="${user.personFirstname}"/></td>
                    </tr>
                    <tr>
                        <th>LastName</th>
                        <td><input type="text" name="LastName" value="${user.personLastname}"/></td>
                    </tr>
                    <tr>
                        <th>Birthdate</th>
                        <td><input type="date" name="Birthdate" value="<fmt:formatDate value="${anUser.personBirthdate}" pattern="yyyy-MM-dd" />"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button class="saveBtn">Save</button>
                        </td>
                    </tr>
                </table>
            </form>

        </div>
        <div class="list">
            <h1>Borrow list : </h1>
            <table>
                <tr>
                    <th>Date</th>
                    <th>Title</th>
                    <th>Return</th>
                </tr>
                <c:forEach var="borrow" items="${user.borrowCollection}">
                    <tr>
                        <td><fmt:formatDate value="${borrow.borrowDate}" pattern="yyyy-MM-dd" /></td>
                        <td>${borrow.bookId.bookTitle}</td>
                        <td class="centered">
                            <c:choose>
                                <c:when test="${not empty borrow.borrowReturn}">
                                    <fmt:formatDate value="${borrow.borrowReturn}" pattern="yyyy-MM-dd" />
                                </c:when>
                                <c:otherwise>
                                    <button class="icon" name="return" onclick="returnBorrow(this, ${borrow.borrowId});return false;">
                                        <img src="img/return.png" alt="return" class="icon">
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                    
                    <form action="addBorrow.do" method="POST">
                     <tr>
                         <td colspan="2">
                             <input type="hidden" name="userId" value="${user.personId}" />
                             <select name="bookID" class="large">
                                 <option value="-1" selected="selected">-</option>
                                 <c:forEach var="book" items="${booksList}">
                                     <option value="${book.bookId}">${book.bookTitle}</option>
                                 </c:forEach>
                             </select>
                         </td>
                         <td class="centred">
                             <button><img src="img/plus.png" alt="plus" class="icon"></button>
                         </td>
                     </tr>
                </form>
            </table>
</body>
</html>