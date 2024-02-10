package com.example.lab13;

import java.io.File;
import java.io.FilenameFilter;

public class ChoiceXXX {
    public String listxxx[];
    protected class OnlyXXX implements FilenameFilter {
        String xxx;
        public OnlyXXX(String xxx) {
            this.xxx = "." + xxx;
        }
        public boolean accept(File d, String name) {
            return name.endsWith(xxx);
        }
    }
    public ChoiceXXX(String d, String xxx) {
        File dir = new File(d);
        if (dir.exists()) {
            listxxx = dir.list(new OnlyXXX(xxx));
        }
    }
}
