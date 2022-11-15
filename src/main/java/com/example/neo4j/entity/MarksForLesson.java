package com.example.neo4j.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class MarksForLesson {

    @Id @GeneratedValue
    private long id;
    private String name;
    private int answer;
    private int question;


    @Override
    public String toString() {
        return "MarksForLesson{" +
                "answer=" + answer +
                ", question=" + question +
                '}';
    }

    public MarksForLesson() {
    }

    public MarksForLesson(String name, int answer, int question) {
        this.name = name;
        this.answer = answer;
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }
}
