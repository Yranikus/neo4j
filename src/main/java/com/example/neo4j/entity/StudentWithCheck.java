package com.example.neo4j.entity;

public class StudentWithCheck extends StudentEntity{

    private int check;

    public StudentWithCheck(int check) {
        this.check = check;
    }

    public StudentWithCheck(String name, int primaryscore, int score, boolean status, int check) {
        super(name, primaryscore, score, status);
        this.check = check;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}


