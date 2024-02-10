<%@ page import="javax.mail.*,javax.mail.internet.*" %>
<%@ page import="java.util.Properties" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Почтовые сообщения</title>
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            flex-direction: column;
        }
        table {
            border-collapse: collapse;
            width: 80%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        pre {
            white-space: pre-wrap;
            margin: 0;
        }
        h2 {
            text-align: center;
            margin-top: 300px;
        }
    </style>
</head>
<body>
<h2>Список сообщений</h2>
<%
    String host = config.getServletContext().getInitParameter("mailHostImap");
    String user = config.getServletContext().getInitParameter("mailUser");
    String password = config.getServletContext().getInitParameter("mailPassword");

    Properties properties = new Properties();
    properties.setProperty("mail.store.protocol", "imaps");
    properties.setProperty("mail.imaps.ssl.protocols", "TLSv1.2");

    Session sess = Session.getInstance(properties);
    Store store;
    Message[] messages;
    Folder inbox;

    store = sess.getStore("imaps");
    store.connect(host, user, password); //устанавливаем соединение с почтовым сервером
    inbox = store.getFolder("INBOX");
    inbox.open(Folder.READ_ONLY);
    messages = inbox.getMessages();
%>

<table>
    <tr>
        <th>Отправитель</th>
        <th>Тема</th>
        <th>Дата</th>
    </tr>
    <% for (int i = messages.length - 1; i > 0; i--) { %>
    <tr>
        <%
            String sender = MimeUtility.decodeText(messages[i].getFrom()[0].toString());
        %>
        <td><%=sender%>
        </td>
        <td><a href="viewMessage.jsp?messageNumber=<%=i%>"><%=messages[i].getSubject()%>
        </a></td>
        <td><%=messages[i].getSentDate()%>
        </td>
    </tr>
    <% }
        inbox.close(false);
        store.close();
    %>
</table>
</body>
</html>
