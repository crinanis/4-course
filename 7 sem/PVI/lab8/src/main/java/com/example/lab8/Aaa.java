package com.example.lab8;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

@WebServlet(urlPatterns = "/Aaa")
public class Aaa extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://192.168.23.128:8080/lab8/Bbb");

        List<NameValuePair> params = Arrays.asList(
                new BasicNameValuePair("QueryParam1", "AaaRequestPost1"),
                new BasicNameValuePair("QueryParam2", "AaaRequestPost2"),
                new BasicNameValuePair("QueryParam3", "AaaRequestPost3")
        );
        httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

        httpPost.addHeader("AaaHeader1", "Aaa requestPost1");
        httpPost.addHeader("AaaHeader2", "Aaa requestPost2");
        httpPost.addHeader("AaaHeader3", "Aaa requestPost3");

        HttpResponse httpResponsePost = httpclient.execute(httpPost);

        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        pw.println("<h2>Body From Bbb response</h2>");
        pw.println(EntityUtils.toString(httpResponsePost.getEntity(), StandardCharsets.UTF_8));

        pw.println("<h2>Headers From Bbb response</h2>");
        for (Header responseHeader : httpResponsePost.getAllHeaders()) {
            pw.println(responseHeader.getName() + "=" + responseHeader.getValue() + "<br>");
        }
        pw.flush();
    }
}

