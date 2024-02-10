package com.example.lab14;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String host = getServletContext().getInitParameter("mailHost");
        String port = getServletContext().getInitParameter("mailPort");
        String user = getServletContext().getInitParameter("mailUser");
        String password = getServletContext().getInitParameter("mailPassword");

        String to = request.getParameter("mail");
        String subject = "Тема письма";
        String messageText = request.getParameter("message");

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.ssl.enable", "true");
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(messageText);
            message.setSentDate(new Date());
            Transport.send(message);
            response.sendRedirect("success.jsp");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
