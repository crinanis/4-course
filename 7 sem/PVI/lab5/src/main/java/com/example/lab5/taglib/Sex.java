package com.example.lab5.taglib;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Sex extends TagSupport {
    private String value = "";
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            String in = "<br/>Male <input name =\"sex\" type = \"radio\" value=\"Male\"/>";
            out.print(in);
            in = "<br/>Female <input name =\"sex\" type = \"radio\" value=\"Female\"/>";
            out.print(in);
        } catch (IOException e) {
            System.out.println("taglib.Sex: " + e);
        }
        return SKIP_BODY;
    }
}
