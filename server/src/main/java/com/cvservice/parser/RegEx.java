package com.cvservice.parser;

public class RegEx {

    public static final String[] skills = {"Java", "Python", "C++", "Haskell", "Anglais", "Go", "Node.js", "Espagnole"};

    public static final String git = "git[hl][ua]b.com/[A-z]*\\.?[A-z0-9]*";

    public static final String mail = "[A-z0-9\\.]*@[A-z]*\\.[A-z]*";

    public static final String phone = "[0-9][0-9][ /\\.\\-]*[0-9][0-9][ /\\.\\-]*[0-9][0-9][ /\\.\\-]*[0-9][0-9][ /\\.\\-]*[0-9][0-9][ /\\.\\-]*";

    public static String getRegEx(String s){
        String regex = "[^A-Za-z]";
        int gap = (int)'a' - (int)'A';
        for(int i = 0; i < s.length(); i++){
            if((int) s.charAt(i) >= (int)'A' && (int) s.charAt(i) <= (int)'Z')
                regex += "[" + s.charAt(i) + (char)((int) s.charAt(i) + gap) + "]";
            else if((int) s.charAt(i) >= (int)'a' && (int) s.charAt(i) <= (int)'z')
                regex += "[" + (char)((int) s.charAt(i) - gap) + s.charAt(i) + "]";
            else if (s.charAt(i) == '.')
                regex += "\\.";
            else
                regex += s.charAt(i);
        }
        return regex + "[^A-Za-z]";
    }
}
