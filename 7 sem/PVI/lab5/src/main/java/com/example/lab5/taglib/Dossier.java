package com.example.lab5.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Dossier extends TagSupport {
    private String action = "";
    public String getAction() {
        return this.action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    @Override
    public int doStartTag() throws JspException {
        String in = "<form method = \"post\" action= \"" + this.action + "\">";
        JspWriter out = pageContext.getOut();
        Out(out, in);
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        String in = "</form>";
        JspWriter out = pageContext.getOut();
        Out(out, in);
        return EVAL_BODY_INCLUDE;
    }

    public void Out(JspWriter out, String in) throws JspException {
        try {
            out.print(in);
        } catch (IOException e) {
            System.out.println("taglib.Dossier: " + e);
        }
    }
}
