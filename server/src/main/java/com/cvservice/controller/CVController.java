package com.cvservice.controller;

import com.cvservice.front.models.FCandidate;
import com.cvservice.models.Candidate;
import com.cvservice.parser.Parser;
import com.cvservice.repositories.db.CVRepository;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class CVController {

    @Autowired
    private CVRepository repository;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestParam("photos") MultipartFile file) throws IOException, TikaException, SAXException {
        String path = request.getServletContext().getRealPath("/") + file.getOriginalFilename();
        byte[] b = file.getBytes();
        Files.write(Path.of(path), b);
        File f = new File(path);
        System.out.println("---- UPLOAD ---- " + f.getName() + "  ----- ");

        System.out.println();
        Candidate candidate = Parser.parser(f);
        repository.save(candidate);
        System.out.println("Succes : " + candidate);
    }

    @GetMapping(value = "/query", produces = {"application/json"})
    public List<FCandidate> getQuery(@RequestParam String keyword){
        System.out.println("----- QUERY ----- " + keyword + " -----");
        if(keyword.contains("PHONE")) {
            Candidate candidate = repository.findByPhone(keyword.split(" ")[1]).get(0);
            System.out.println(keyword + " : " + candidate);
            return List.of(new FCandidate(candidate));
        }
        List<String> skills = List.of(keyword.split(","));
        List<Candidate> candidates = repository.findBySkillsName(skills);
        List<FCandidate> fCandidates = new ArrayList<>();
        for(Candidate c : candidates) {
            System.out.println(keyword + " : " + c);
            fCandidates.add(new FCandidate(c));
        }
        return fCandidates;
    }
}
