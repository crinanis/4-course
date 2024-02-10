package com.example.lab5.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Firstname extends TagSupport {
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
            String in = "<label>Firstname</label>"
                    + "<input name =\"firstname\" type = \"text\" width = \"150\" maxlength= \"14\"";
            out.print(in + (this.value.equals("") ? " " : "value =\"" + this.value + "\" />"));
        } catch (IOException e) {
            System.out.println("taglib.Firstname: " + e);
        }
        return SKIP_BODY;
    }
}
