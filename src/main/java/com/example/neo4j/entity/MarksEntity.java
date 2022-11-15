package com.example.neo4j.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node(labels = "marks")
public class MarksEntity {


    @Id
    @GeneratedValue
    private long id;
    private int question;
    private int answer;

    @Relationship(type = "ATTEND", direction = Relationship.Direction.OUTGOING)
    public DateEntity dateEntity;


    public MarksEntity() {
    }

    public MarksEntity(long id, int question, int answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public DateEntity getDateEntity() {
        return dateEntity;
    }

    public void setDateEntity(DateEntity dateEntity) {
        this.dateEntity = dateEntity;
    }
}
