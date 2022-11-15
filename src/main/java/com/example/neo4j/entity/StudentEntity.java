package com.example.neo4j.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;


@Node(labels = "student")
public class StudentEntity {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int primaryscore;
    private int score;
    private boolean status;



    public StudentEntity() {
    }


    public StudentEntity(String name, int primaryscore, int score, boolean status) {
        this.name = name;
        this.primaryscore = primaryscore;
        this.score = score;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrimaryscore() {
        return primaryscore;
    }

    public void setPrimaryscore(int primaryscore) {
        this.primaryscore = primaryscore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", primaryscore=" + primaryscore +
                ", score=" + score +
                ", status=" + status +
                '}';
    }
}
