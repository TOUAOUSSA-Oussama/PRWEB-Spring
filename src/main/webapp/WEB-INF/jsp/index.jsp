<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <title>Login Page</title>
    </head>
    <body>
        <div class="login">
            <h1>Library Login :</h1>
            <form method="POST" action="index.do">
                <p>Give your login :</p>
                <input type="text" name="login" placeholder="Your login" />
                <p>Give your password :</p>
                <input type="password" name="password" placeholder="Your password" />
                <br>
                <button class="loginBtn">Login</button>
            </form>
        </div>

    </body>
</html>