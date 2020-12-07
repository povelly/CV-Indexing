package com.cvservice.parser;


import com.cvservice.models.Candidate;
import com.cvservice.models.Skill;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.image.ImageParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Parser {

    public static void main(String[] args) throws IOException, TikaException, SAXException {
        parser(new File("/home/sslye/Workspace/DAAAAR/tourist-Unesco/cvtheque/Test_Test.png"));
    }


    public static Candidate parser(File file) throws TikaException, SAXException, IOException { // CV_FIRSTNAME_LASTNAME.format
        return parser(new FileInputStream(file), file.getName());

    }

    public static Candidate parser(InputStream inputStream, String name) throws TikaException, SAXException, IOException {
        System.out.println(name);
        String[] s = name.split("\\.");
        String format = s[1];
        String lastname = s[0].split("_")[0];
        String firstname = s[0].split("_")[1];


        ImageParser ip = new ImageParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        String text = "";
        AutoDetectParser parser = new AutoDetectParser();
        parser.parse(inputStream, handler, metadata, context);
        return parsing(handler.toString(), firstname, lastname);

    }

    public static Candidate parsing(String file, String firstname, String lastname){

        String phone = ""; String git = ""; String mail = "";
        ArrayList<Skill> skills = new ArrayList<>();


        for(String l : RegEx.skills)
            if (Pattern.compile(RegEx.getRegEx(l)).matcher(file).find())
                skills.add(new Skill(l));
        Matcher matcher = Pattern.compile(RegEx.git).matcher(file);
        if (matcher.find())
            git = matcher.group();
        matcher = Pattern.compile(RegEx.mail).matcher(file);
        if (matcher.find())
            mail = matcher.group();
        matcher = Pattern.compile(RegEx.phone).matcher(file);
        if (matcher.find()) {
            phone = matcher.group();
            phone = phone.replace(".","").replace("-","").replace(" ","").replace("/","");
        }
        return new Candidate(phone,firstname, lastname, mail, git, skills);
    }


}
