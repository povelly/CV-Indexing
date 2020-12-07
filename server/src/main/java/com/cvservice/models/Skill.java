package com.cvservice.models;

import lombok.AllArgsConstructor;

import org.springframework.data.annotation.Id;

@AllArgsConstructor
public class Skill {
    @Id
    private String name;

    public String toString(){
        return name;
    }

}
