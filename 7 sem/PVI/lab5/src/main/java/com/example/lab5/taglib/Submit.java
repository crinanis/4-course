package com.example.lab5.taglib;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Submit extends TagSupport {
    private String value = "";
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            String in = "<input type = \"submit\" value = \"OK\" />";
            out.print(in);
            in = "<input type = \"button\" value = \"Cancel\" />";
            out.print(in);
        } catch (IOException e) {
            System.out.println("taglib.Submit: " + e);
        }
        return SKIP_BODY;
    }
}
