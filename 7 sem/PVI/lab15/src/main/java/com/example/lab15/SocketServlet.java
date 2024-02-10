package com.example.lab15;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class SocketServlet {
    @OnOpen
    public void onOpen(Session session) {
        try {
            while (true) {
                String message = new SimpleDateFormat("HH:mm:ss").format(new Date());
                session.getBasicRemote().sendText(message); //базовый метод для отправки сообщений через веб-сокет
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
