package com.revature.jobpostservice.model;

import lombok.Data;

import java.util.List;

@Data
public class Resume {


    private User user;
    private List<Skills> skills;
    private List<Education> education;
    private List<Experience> experience;
    private List<Language> languages;
    private Summary summary;
}