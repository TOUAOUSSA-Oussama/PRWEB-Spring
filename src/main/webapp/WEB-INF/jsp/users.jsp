<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Users page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>
        <div class="list">
            <table class="noborder">
            <tr>
                <td class="noborder"><h1>List of users :</h1></td>
                <td class="noborder" style="text-align:right">
                    <form action="switchToBooks.do" method="POST"><button>Switch books</button></form>
                </td>
            </tr>
        </table>
            <table>
                <tr>
                    <th>User #</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>Birthdate</th>
                    <th></th>
                </tr>
                <c:forEach var="anUser" items="${usersList}">
                    <tr>
                        <td>${anUser.personId}</td>
                        <td>${anUser.personFirstname}</td>
                        <td>${anUser.personLastname}</td>
                        <td><fmt:formatDate value="${anUser.personBirthdate}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <form class="iconForm" method="POST" action="#">
                                <input type="hidden" name="id" value="${anUser.personId}" />
                                <button name="edit" formaction="editUser.do">
                                    <img src="img/edit.png" alt="edit" class="icon" />
                                </button>
                                <button  name="delete" formaction="deleteUser.do">
                                    <img src="img/delete.png" alt="edit" class="icon" />
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <td colspan="4"></td>
                <td>
                    <form class="iconForm" method="POST" action="createUser.do">
                        <button>
                            <img src="img/plus.png" alt="edit" class="icon" />
                        </button>
                    </form>
                </td>
            </tr><tr>

    </table>

</div>
</body>

</html>
