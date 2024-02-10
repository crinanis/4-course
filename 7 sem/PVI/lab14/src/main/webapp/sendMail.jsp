<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Отправка почтового сообщения</title>
</head>
<body>
<form action="MailServlet" method="post">
    Почтовый ящик: <input type="text" name="mail" required><br>
    Сообщение: <textarea name="message" required></textarea><br>
    <input type="submit" value="Отправить">
</form>
</body>
</html>
