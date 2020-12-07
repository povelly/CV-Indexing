package com.cvservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.annotation.Id;

import java.util.List;


@AllArgsConstructor
@Getter
@Document(indexName = "cv", type = "candidate")
public class Candidate {

    @Id
    private String phone;

    private String firstName;

    private String lastName;

    private String mail;

    private String git;


    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Skill> skills;


    public String toString(){
        return firstName + " " + lastName + " : " + phone + "|" + mail + "|" + git;
    }
}
