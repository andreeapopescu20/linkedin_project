package com.endava.pages;

import java.util.List;

/**
 * Created by andpopescu on 1/10/2017.
 */
public class Candidate {
    private String name;
    private String contactPerson;
    private List<String> skills;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getContactPerson(){
        return contactPerson;
    }

    public void setContactPerson(String contactPerson){
        this.contactPerson = contactPerson;
    }

    public List<String> getSkills(){
        return skills;
    }

    public void setSkills(List<String> skills){
        this.skills = skills;
    }









}
