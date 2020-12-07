package com.cvservice.front.models;

import com.cvservice.models.Candidate;
import lombok.Getter;

@Getter
public class FCandidate {

    private String nom, prenom, tel, mail, git;

    public FCandidate(Candidate candidate){
        nom = candidate.getLastName();
        prenom = candidate.getFirstName();
        git = candidate.getGit();
        mail = candidate.getMail();
        tel = candidate.getPhone();
    }


}
