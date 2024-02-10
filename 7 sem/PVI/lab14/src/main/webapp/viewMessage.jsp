<%@ page import="javax.mail.*,javax.mail.internet.*" %>
<%@ page import="java.util.Properties" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Просмотр сообщения</title>
</head>
<body>
<%
    String host = config.getServletContext().getInitParameter("mailHostImap");
    String user = config.getServletContext().getInitParameter("mailUser");
    String password = config.getServletContext().getInitParameter("mailPassword");

    Properties properties = new Properties();
    properties.setProperty("mail.store.protocol", "imaps");
    properties.setProperty("mail.imaps.ssl.protocols", "TLSv1.2");

    Session sess = Session.getInstance(properties);
    Store store;
    Message message;
    Folder inbox;
    try {
        store = sess.getStore("imaps");
        store.connect(host, user, password);

        inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        int messageNumber = Integer.parseInt(request.getParameter("messageNumber"));
        message = inbox.getMessage(messageNumber + 1);

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }

%>

<h3>Просмотр сообщения</h3>
<table border="1" style="border-collapse: collapse; width: 80%;">
    <tr>
        <th>Отправитель</th>
        <%
            String sender = MimeUtility.decodeText(message.getFrom()[0].toString());
        %>
        <td><%=sender%></td>
    </tr>
    <tr>
        <th>Тема</th>
        <td><%=message.getSubject()%>
        </td>
    </tr>
    <tr>
        <th>Дата</th>
        <td><%=message.getSentDate()%>
        </td>
    </tr>
</table>
<hr/>
<h3>Содержание сообщения:</h3>
<%
    Object content;
    try {
        content = message.getContent();
        if (content instanceof String) {
            out.println("<pre>" + content + "</pre>");
        } else if (content instanceof Multipart) {
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.getContentType().startsWith("text/plain") || bodyPart.getContentType().startsWith("text/html")) {
                    out.println("<pre>" + bodyPart.getContent() + "</pre>");
                } else {
                    out.println("<p>Attachment: " + bodyPart.getFileName() + "</p>");
                }
            }
        }
        inbox.close(false);
        store.close();
    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
%>
</body>
</html>
