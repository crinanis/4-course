package com.example.lab5.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Lastname extends TagSupport {
    private String value = "";
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        try {
            String in = "<br/><label>Lastname</label>"
                    + "<input name =\"lastname\" type = \"text\" width = \"150\" maxlength= \"14\"";
            out.print(in + (this.value.equals("") ? " " : "value =\"" + this.value + "\" />"));
        } catch (IOException e) {
            System.out.println("taglib.Lastname: " + e);
        }
        return SKIP_BODY;
    }
}
